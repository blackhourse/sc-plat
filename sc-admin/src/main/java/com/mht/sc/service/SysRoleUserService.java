package com.mht.sc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mht.sc.entity.SysRole;
import com.mht.sc.entity.SysRoleUser;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author maht
 * @since 2020-11-17
 */
public interface SysRoleUserService extends IService<SysRoleUser> {

    int deleteUserRole(Long userId, Long roleId);

    int saveUserRoles(Long userId, Long roleId);

    /**
     * 根据用户id获取角色
     *
     * @param userId
     * @return
     */
    List<SysRole> findRolesByUserId(Long userId);

    /**
     * 根据用户ids 获取
     *
     * @param userIds
     * @return
     */
    List<SysRole> findRolesByUserIds(List<Long> userIds);

}
