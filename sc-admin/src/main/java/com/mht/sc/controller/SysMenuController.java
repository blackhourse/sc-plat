package com.mht.sc.controller;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import com.mht.common.api.CommonResult;
import com.mht.common.api.PageResult;
import com.mht.common.constant.CommonConstant;
import com.mht.sc.entity.SysMenu;
import com.mht.sc.entity.SysRole;
import com.mht.sc.dto.SysGrantedMenuRoleDto;
import com.mht.sc.dto.SysMenuAddOrUpdateDto;
import com.mht.sc.service.SysMenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author maht
 * @since 2020-11-17
 */
@Slf4j
@RestController
@RequestMapping("/sysMenu")
@Api(value = "菜单api")
public class SysMenuController {

    @Autowired
    private SysMenuService menuService;


    /**
     * 添加菜单 或者 更新
     *
     * @param sysMenuAddOrUpdateDto
     * @return
     */
    @ApiOperation(value = "新增菜单")
    @PostMapping("saveOrUpdate")
    public CommonResult saveOrUpdate(@RequestBody SysMenuAddOrUpdateDto sysMenuAddOrUpdateDto) {
        return CommonResult.success(menuService.addOrUpdate(sysMenuAddOrUpdateDto));
    }


    /**
     * 删除菜单
     *
     * @param id
     */
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable Long id) {
        try {
            menuService.removeById(id);
            return CommonResult.success("操作成功");
        } catch (Exception ex) {
            log.error("memu-delete-error", ex);
            return CommonResult.failed("操作失败");
        }
    }


    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/findAlls")
    public PageResult<SysMenu> findAlls() {
        List<SysMenu> list = menuService.findAll();
        return PageResult.<SysMenu>builder().data(list).code(0).count((long) list.size()).build();
    }

    @ApiOperation(value = "获取菜单以及顶级菜单")
    @GetMapping("/findOnes")
    public PageResult<SysMenu> findOnes() {
        List<SysMenu> list = menuService.findOnes();
        return PageResult.<SysMenu>builder().data(list).code(0).count((long) list.size()).build();
    }


    @ApiOperation(value = "根据roleId获取对应的菜单")
    @GetMapping("/{roleId}/menus")
    public List<Map<String, Object>> findMenusByRoleId(@PathVariable Long roleId) {
        Set<Long> roleIds = new HashSet<>();
        roleIds.add(roleId);
        //获取该角色对应的菜单
        List<SysMenu> roleMenus = menuService.findByRoles(roleIds);
        //全部的菜单列表
        List<SysMenu> allMenus = menuService.findAll();
        List<Map<String, Object>> authTrees = new ArrayList<>();

        Map<Long, SysMenu> roleMenusMap = roleMenus.stream().collect(Collectors.toMap(SysMenu::getId, SysMenu -> SysMenu));

        for (SysMenu sysMenu : allMenus) {
            Map<String, Object> authTree = new HashMap<>();
            authTree.put("id", sysMenu.getId());
            authTree.put("name", sysMenu.getName());
            authTree.put("pId", sysMenu.getParentId());
            authTree.put("open", true);
            authTree.put("checked", false);
            if (roleMenusMap.get(sysMenu.getId()) != null) {
                authTree.put("checked", true);
            }
            authTrees.add(authTree);
        }
        return authTrees;
    }

    @ApiOperation(value = "根据roleCodes获取对应的权限")
    @SuppressWarnings("unchecked")
    @Cacheable(value = "menu", key = "#roleCodes")
    @GetMapping("/{roleCodes}")
    public List<SysMenu> findMenuByRoles(@PathVariable String roleCodes) {
        List<SysMenu> result = null;
        if (StringUtils.isNotEmpty(roleCodes)) {
            Set<String> roleSet = (Set<String>) Convert.toCollection(HashSet.class, String.class, roleCodes);
            result = menuService.findByRoleCodes(roleSet, CommonConstant.PERMISSION);
        }
        return result;
    }

    /**
     * 给角色分配菜单
     */
    @ApiOperation(value = "角色分配菜单")
    @PostMapping("/granted")
    public CommonResult setMenuToRole(@RequestBody SysGrantedMenuRoleDto sysGrantedMenuRoleDto) {
        menuService.setMenuToRole(sysGrantedMenuRoleDto);
        return CommonResult.success("操作成功");
    }


    /**
     * 当前登录用户的菜单
     *
     * @return
     */
    @GetMapping("/current/{id}")
    @ApiOperation(value = "查询当前用户菜单")
    public List<SysMenu> findMyMenu(@PathVariable Long id) {
        List<SysRole> userRoles = menuService.findUserRoles(id);
        if (CollectionUtil.isEmpty(userRoles)) {
            return Collections.emptyList();
        }
        List<SysMenu> menus = menuService.findByRoleCodes(userRoles.parallelStream().map(SysRole::getCode).collect(Collectors.toSet()), CommonConstant.MENU);
        return treeBuilder(menus);
    }

    /**
     * 两层循环实现建树
     *
     * @param sysMenus
     * @return
     */
    public static List<SysMenu> treeBuilder(List<SysMenu> sysMenus) {
        List<SysMenu> menus = new ArrayList<>();
        for (SysMenu sysMenu : sysMenus) {
            if (Objects.equals(-1L, sysMenu.getParentId())) {
                menus.add(sysMenu);
            }
            for (SysMenu menu : sysMenus) {
                if (menu.getParentId().equals(sysMenu.getId())) {
                    if (sysMenu.getSubMenus() == null) {
                        sysMenu.setSubMenus(new ArrayList<>());
                    }
                    sysMenu.getSubMenus().add(menu);
                }
            }
        }
        return menus;
    }

}

