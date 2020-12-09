package cn.boot.st.securityserver.feign.factory;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.securityserver.dataobject.bo.RoleResourceVo;
import cn.boot.st.securityserver.feign.PermissionFeignService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

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
                return success(new HashSet<>());
            }

            @Override
            public CommonResult<Boolean> hasSuperAdmin(Collection<Integer> roleIds) {
                return success(Boolean.FALSE);
            }

            @Override
            public Set<Integer> selectListByPermissions(Collection<String> permissions) {
                log.error("校验管理员是否拥有指定权限失败:{}", permissions);
                return Collections.EMPTY_SET;
            }

            @Override
            public List<RoleResourceVo> selectListByResourceIds(Collection<Integer> permissions) {
                return new ArrayList<>();
            }


        };
    }
}
