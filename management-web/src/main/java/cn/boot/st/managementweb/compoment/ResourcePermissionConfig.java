package cn.boot.st.managementweb.compoment;

import cn.boot.common.framework.dataobject.entity.PermissionEntityVO;
import cn.boot.common.framework.util.URLConvertUtil;
import cn.boot.st.security.annotations.RequiresPermissions;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-18
 **/

@Slf4j
@Component
public class ResourcePermissionConfig implements CommandLineRunner {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private WebApplicationContext applicationContext;

    @Value("${spring.application.name}")
    private String applicationName;


    @Getter
    @Setter
    private List<PermissionEntityVO> permissionEntities = Lists.newArrayList();

    @Override
    public void run(String... args) {
        log.info("===============ResourcePermissionConfig==============");
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        map.keySet().forEach(mappingInfo -> {
            HandlerMethod handlerMethod = map.get(mappingInfo);
            RequiresPermissions method = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), RequiresPermissions.class);
            Optional.ofNullable(method)
                    .ifPresent(resourcePermission -> mappingInfo
                            .getPatternsCondition()
                            .getPatterns()
                            .forEach(url -> {
                                String strUrl = URLConvertUtil.capture(url);
                                String permission = URLConvertUtil.convert(url);
                                permissionEntities.add(PermissionEntityVO
                                        .builder()
                                        .name(Arrays.toString(method.value()))
                                        .permission(permission)
                                        .serviceId(applicationName)
                                        .url(strUrl)
                                        .build());
                            }));
            RequiresPermissions controller = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), RequiresPermissions.class);
            Optional.ofNullable(controller)
                    .ifPresent(resourcePermission -> mappingInfo
                            .getPatternsCondition()
                            .getPatterns()
                            .forEach(url -> {
                                String strUrl = URLConvertUtil.capture(url);
                                String permission = URLConvertUtil.convert(url);
                                if (StrUtil.isNotBlank(permission)) {
                                    assert method != null;
                                    permissionEntities.add(PermissionEntityVO
                                            .builder()
                                            .name(Arrays.toString(method.value()))
                                            .permission(permission)
                                            .serviceId(applicationName)
                                            .url(strUrl)
                                            .build());
                                }
                            }));
        });
        if (CollUtil.isNotEmpty(permissionEntities)) {
            redisTemplate.opsForValue().set(applicationName, permissionEntities);
        }
    }


}
