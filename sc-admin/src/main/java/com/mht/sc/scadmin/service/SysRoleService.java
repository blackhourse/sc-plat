package com.mht.sc.scadmin.service;

import com.mht.common.api.CommonResult;
import com.mht.common.api.PageResult;
import com.mht.sc.scadmin.dto.SysRoleAddOrUpdateDto;
import com.mht.sc.scadmin.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;


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


    CommonResult saveOrUpdateRole(SysRoleAddOrUpdateDto sysRole);


    /**
     * 查询所有角色
     * @return
     */
    List<SysRole> findAll();

    void deleteRole(Long id);


}
