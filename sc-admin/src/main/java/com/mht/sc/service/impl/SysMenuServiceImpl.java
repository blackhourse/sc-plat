package com.mht.sc.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mht.sc.convert.SysMenuConvert;
import com.mht.sc.entity.SysMenu;
import com.mht.sc.entity.SysRole;
import com.mht.sc.entity.SysRoleMenu;
import com.mht.sc.entity.SysRoleUser;
import com.mht.sc.mapper.SysMenuMapper;
import com.mht.sc.dto.SysGrantedMenuRoleDto;
import com.mht.sc.dto.SysMenuAddOrUpdateDto;
import com.mht.sc.service.SysMenuService;
import com.mht.sc.service.SysRoleMenuService;
import com.mht.sc.service.SysRoleService;
import com.mht.sc.service.SysRoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author maht
 * @since 2020-11-17
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysRoleMenuService roleMenuService;

    @Autowired
    private SysRoleUserService roleUserService;

    @Autowired
    private SysRoleService sysRoleService;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void setMenuToRole(SysGrantedMenuRoleDto sysGrantedMenuRoleDto) {
        roleMenuService.delete(sysGrantedMenuRoleDto.getRoleId(), null);

        if (!CollectionUtils.isEmpty(sysGrantedMenuRoleDto.getMenuIds())) {
            List<SysRoleMenu> roleMenus = new ArrayList<>(sysGrantedMenuRoleDto.getMenuIds().size());
            sysGrantedMenuRoleDto.getMenuIds().forEach(menuId -> roleMenus.add(new SysRoleMenu(sysGrantedMenuRoleDto.getRoleId(), menuId)));
            roleMenuService.saveBatch(roleMenus);
        }
    }

    /**
     * 角色菜单列表
     *
     * @param roleIds
     * @return
     */
    @Override
    public List<SysMenu> findByRoles(Set<Long> roleIds) {
        return roleMenuService.findMenusByRoleIds(roleIds, null);
    }

    /**
     * 角色菜单列表
     *
     * @param roleIds 角色ids
     * @param roleIds 是否菜单
     * @return
     */
    @Override
    public List<SysMenu> findByRoles(Set<Long> roleIds, Integer type) {
        return roleMenuService.findMenusByRoleIds(roleIds, type);
    }

    @Override
    public List<SysRole> findUserRoles(Long userId) {
        List<SysRole> sysRoles = null;

        LambdaQueryWrapper<SysRoleUser> queryWrapper = new LambdaQueryWrapper<SysRoleUser>().eq(SysRoleUser::getUserId, userId);
        List<SysRoleUser> list = roleUserService.list(queryWrapper);
        if (CollectionUtil.isNotEmpty(list)) {
            List<Long> roleIds = list.stream().map(SysRoleUser::getRoleId).collect(Collectors.toList());
            sysRoles = sysRoleService.listByIds(roleIds);
        }
        return sysRoles;
    }

    @Override
    public List<SysMenu> findByRoleCodes(Set<String> roleCodes, Integer type) {
        return roleMenuService.findMenusByRoleCodes(roleCodes, type);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysMenu addOrUpdate(SysMenuAddOrUpdateDto sysMenuAddOrUpdateDto) {
        Date date = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
        SysMenu sysMenu = SysMenuConvert.MAPPER.convert(sysMenuAddOrUpdateDto);
        if (Objects.isNull(sysMenuAddOrUpdateDto.getId())) {
            sysMenu.setCreateTime(date);
        }
        this.saveOrUpdate(sysMenu);
        return sysMenu;
    }

    /**
     * 查询所有菜单
     */
    @Override
    public List<SysMenu> findAll() {
        return baseMapper.selectList(
                new QueryWrapper<SysMenu>().orderByAsc("sort")
        );
    }

    /**
     * 查询所有一级菜单
     */
    @Override
    public List<SysMenu> findOnes() {
        return baseMapper.selectList(
                new QueryWrapper<SysMenu>()
                        .eq("type", 1)
                        .orderByAsc("sort")
        );
    }
}
