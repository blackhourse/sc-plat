package com.mht.sc.scadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mht.sc.scadmin.dto.SysUserDto;
import com.mht.sc.scadmin.entity.SysUser;
import com.mht.sc.scadmin.util.Result;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author maht
 * @since 2020-11-17
 */
public interface SysUserService extends IService<SysUser> {
    /**
     * 新增/修改
     *
     * @param sysUser
     * @return
     */
    Long saveOrUpdateUser(SysUserDto sysUser);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    Boolean delUser(Long id);

    /**
     * 修改密码
     *
     * @param id
     * @param oldPassword
     * @param newPassword
     * @return
     */
    Result<String> updatePassword(Long id, String oldPassword, String newPassword);

    /**
     * 修改用户状态
     * @param id
     * @param enable
     * @return
     */
    Result<String> updateEnable(Long id, Integer enable);

}
