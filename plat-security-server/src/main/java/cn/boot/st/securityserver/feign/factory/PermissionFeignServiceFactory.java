package cn.boot.st.securityserver.feign.factory;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.securityserver.feign.PermissionFeignService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static cn.boot.common.framework.vo.CommonResult.success;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-09 09:21
 **/
@Slf4j
@Component
public class PermissionFeignServiceFactory implements FallbackFactory<PermissionFeignService> {

    @Override
    public PermissionFeignService create(Throwable throwable) {
        return new PermissionFeignService() {

            @Override
            public CommonResult<Set<Integer>> listAdminRoles(Integer adminId) {
                log.error("获取用户角色错误:{}", adminId);
                return success(new HashSet<Integer>());
            }

            @Override
            public CommonResult<Set<Integer>> listRoleResources(Integer roleId) {
                log.error("获得角色拥有的资源编号:{}", roleId);
                return success(new HashSet<Integer>());
            }

            @Override
            public CommonResult<Boolean> hasSuperAdmin(Collection<Integer> roleIds) {
                return success(Boolean.FALSE);
            }
        };
    }
}
