package com.mht.sc.scadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mht.sc.scadmin.entity.SysMenu;
import com.mht.sc.scadmin.entity.SysRoleMenu;

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
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    int save(Long roleId, Long menuId);

    int delete(Long roleId, Long menuId);

    List<SysMenu> findMenusByRoleIds(Set<Long> roleIds, Integer type);

    List<SysMenu> findMenusByRoleCodes(Set<String> roleCodes, Integer type);

}
