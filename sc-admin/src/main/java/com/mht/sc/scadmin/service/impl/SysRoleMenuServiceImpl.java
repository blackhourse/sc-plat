package com.mht.sc.scadmin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mht.sc.scadmin.entity.SysMenu;
import com.mht.sc.scadmin.entity.SysRoleMenu;
import com.mht.sc.scadmin.mapper.SysRoleMenuMapper;
import com.mht.sc.scadmin.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author maht
 * @since 2020-11-17
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Resource
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public int save(Long roleId, Long menuId) {
        return sysRoleMenuMapper.save(roleId, menuId);
    }

    @Override
    public int delete(Long roleId, Long menuId) {
        return sysRoleMenuMapper.delete(roleId, menuId);
    }

    @Override
    public List<SysMenu> findMenusByRoleIds(Set<Long> roleIds, Integer type) {
        return sysRoleMenuMapper.findMenusByRoleIds(roleIds, type);
    }

    @Override
    public List<SysMenu> findMenusByRoleCodes(Set<String> roleCodes, Integer type) {
        return sysRoleMenuMapper.findMenusByRoleCodes(roleCodes, type);
    }
}
