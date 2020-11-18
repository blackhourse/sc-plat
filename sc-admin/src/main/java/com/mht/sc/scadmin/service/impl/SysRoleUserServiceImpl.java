package com.mht.sc.scadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mht.sc.scadmin.entity.SysRole;
import com.mht.sc.scadmin.entity.SysRoleUser;
import com.mht.sc.scadmin.mapper.SysRoleUserMapper;
import com.mht.sc.scadmin.service.SysRoleUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author maht
 * @since 2020-11-17
 */
@Service
public class SysRoleUserServiceImpl extends ServiceImpl<SysRoleUserMapper, SysRoleUser> implements SysRoleUserService {
    @Resource
    private SysRoleUserMapper sysUserRoleMapper;

    @Override
    public int deleteUserRole(Long userId, Long roleId) {
        return sysUserRoleMapper.deleteUserRole(userId, roleId);
    }

    @Override
    public int saveUserRoles(Long userId, Long roleId) {
        return sysUserRoleMapper.saveUserRoles(userId, roleId);
    }

    @Override
    public List<SysRole> findRolesByUserId(Long userId) {
        return sysUserRoleMapper.findRolesByUserId(userId);
    }

    @Override
    public List<SysRole> findRolesByUserIds(List<Long> userIds) {
        return sysUserRoleMapper.findRolesByUserIds(userIds);
    }
}
