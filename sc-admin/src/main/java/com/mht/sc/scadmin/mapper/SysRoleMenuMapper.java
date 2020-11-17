package com.mht.sc.scadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mht.sc.scadmin.entity.SysMenu;
import com.mht.sc.scadmin.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author maht
 * @since 2020-11-17
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {
    @Insert("insert into sys_role_menu(role_id, menu_id) values(#{roleId}, #{menuId})")
    int save(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    int delete(@Param("roleId") Long roleId, @Param("menuId") Long menuId);

    List<SysMenu> findMenusByRoleIds(@Param("roleIds") Set<Long> roleIds, @Param("type") Integer type);

    List<SysMenu> findMenusByRoleCodes(@Param("roleCodes") Set<String> roleCodes, @Param("type") Integer type);
}
