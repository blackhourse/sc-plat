package cn.boot.st.system.config;

import cn.boot.common.framework.constant.AuthConstants;
import cn.boot.common.framework.dataobject.dto.PermissionVo;
import cn.boot.common.framework.dataobject.entity.PermissionEntityVO;
import cn.boot.common.framework.util.StringUtils;
import cn.boot.st.redis.RedisService;
import cn.boot.st.system.dataobject.ResourceDO;
import cn.boot.st.system.dataobject.RoleResourceDO;
import cn.boot.st.system.mapper.ResourceMapper;
import cn.boot.st.system.mapper.RoleResourceMapper;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-27
 **/
@Slf4j
@Component
public class ResourcePermissionConfig implements CommandLineRunner {

    @Autowired
    private RedisService redisService;

    private static final String PERSISTENCE_NAME = "PERSISTENCE_NAME";

    @Getter
    @Setter
    private Map<String, PermissionEntityVO> permissionEntities = Maps.newConcurrentMap();

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public void run(String... args) {

        log.info("InitResourceRolesCacheRunner run");

        List<RoleResourceDO> roleResourceDOS = roleResourceMapper.selectList(null);
        if (roleResourceDOS.size() <= 0) {
            log.info("为找到角色资源");
        }

        Set<Integer> roleResourceDosSet = roleResourceDOS.stream().map(RoleResourceDO::getResourceId).collect(Collectors.toSet());
        List<ResourceDO> resourceDOS = resourceMapper.selectBatchIds(roleResourceDosSet);
        Map<Integer, ResourceDO> collect = new HashMap<>();
        for (ResourceDO resourceDO : resourceDOS) {
            if (StringUtils.hasText(resourceDO.getPermission())) {
                collect.put(resourceDO.getId(), resourceDO);
            }
        }
        Map<Integer, List<Integer>> roleResourceMap = roleResourceDOS.stream()
                .collect(Collectors.groupingBy(RoleResourceDO::getRoleId,
                        Collectors.mapping(RoleResourceDO::getResourceId, Collectors.toList())));

        Map<String, List<PermissionVo>> map = new HashMap<>();
        Iterator<Map.Entry<Integer, List<Integer>>> iterator = roleResourceMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, List<Integer>> next = iterator.next();
            Integer key = next.getKey();
            String prefix = AuthConstants.AUTHORITY_PREFIX + key;
            List<Integer> value = next.getValue();
            List<PermissionVo> permissionUrls = value.stream().filter(s -> collect.containsKey(s)).map(s -> {
                PermissionVo permissionVo = new PermissionVo();
                ResourceDO resourceDO = collect.get(s);
                return permissionVo.setName(resourceDO.getName()).setUrl(resourceDO.getPermission());
            }).collect(Collectors.toList());
            map.put(prefix, permissionUrls);
        }
        redisService.del(PERSISTENCE_NAME);
        redisService.hmset(PERSISTENCE_NAME, map);

    }

}
