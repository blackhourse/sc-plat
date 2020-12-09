package cn.boot.st.securityserver.feign;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.securityserver.feign.factory.PermissionFeignServiceFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Set;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-08 16:51
 **/
@FeignClient(name = "management-web", fallbackFactory = PermissionFeignServiceFactory.class, decode404 = true)
public interface PermissionFeignService {
    /**
     * 获取用户角色
     *
     * @param adminId
     * @return
     */
    @GetMapping("/permission/list-admin-roles")
    CommonResult<Set<Integer>> listAdminRoles(@RequestParam("adminId") Integer adminId);

    /**
     * 获得角色拥有的资源编号
     *
     * @param roleId
     * @return
     */
    @GetMapping("/permission/list-role-resources")
    CommonResult<Set<Integer>> listRoleResources(@RequestParam("roleId") Integer roleId);

    /**
     * 判断角色是否有超级管理员
     *
     * @param roleIds
     * @return
     */
    @GetMapping("/admin/roleIds")
    CommonResult<Boolean> hasSuperAdmin(@RequestParam("roleIds") Collection<Integer> roleIds);
}
