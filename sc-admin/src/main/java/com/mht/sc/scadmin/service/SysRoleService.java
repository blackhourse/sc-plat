package com.mht.sc.scadmin.service;

import com.mht.sc.scadmin.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mht.sc.scadmin.util.PageResult;
import com.mht.sc.scadmin.util.Result;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author maht
 * @since 2020-11-17
 */
public interface SysRoleService extends IService<SysRole> {


    /**
     * 角色列表
     * @param params
     * @return
     */
    PageResult<SysRole> findRoles(Map<String, Object> params);

    void saveRole(SysRole sysRole);


    Result saveOrUpdateRole(SysRole sysRole);


    /**
     * 查询所有角色
     * @return
     */
    List<SysRole> findAll();

    void deleteRole(Long id);


}
