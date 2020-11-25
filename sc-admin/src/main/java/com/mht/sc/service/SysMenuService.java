package com.mht.sc.service;

import com.mht.sc.dto.SysGrantedMenuRoleDto;
import com.mht.sc.dto.SysMenuAddOrUpdateDto;
import com.mht.sc.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mht.sc.entity.SysRole;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author maht
 * @since 2020-11-17
 */
public interface SysMenuService extends IService<SysMenu> {


    /**
     * 添加/修改菜单
     * @param sysMenuAddOrUpdateDto
     * @return
     */
    SysMenu addOrUpdate(SysMenuAddOrUpdateDto sysMenuAddOrUpdateDto);

    /**
     * 查询所有菜单
     */
    List<SysMenu> findAll();

    /**
     * 查询所有一级菜单
     */
    List<SysMenu> findOnes();

    /**
     * 角色分配菜单
     * @param sysGrantedMenuRoleDto
     */
    void setMenuToRole(SysGrantedMenuRoleDto sysGrantedMenuRoleDto);

    /**
     * 角色菜单列表
     * @param roleIds 角色ids
     * @return
     */
    List<SysMenu> findByRoles(Set<Long> roleIds);

    /**
     * 角色菜单列表
     * @param roleIds 角色ids
     * @param roleIds 是否菜单
     * @return
     */
    List<SysMenu> findByRoles(Set<Long> roleIds, Integer type);

    /**
     * 获取用户的角色
     * @param userId
     * @return
     */
    List<SysRole> findUserRoles(Long userId);

    /**
     * 角色菜单列表
     * @param roleCodes
     * @return
     */
    List<SysMenu> findByRoleCodes(Set<String> roleCodes, Integer type);


}
