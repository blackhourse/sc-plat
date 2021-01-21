package cn.boot.st.system.service;

import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.system.dto.RoleCreateDTO;
import cn.boot.st.system.dto.RolePageDTO;
import cn.boot.st.system.dto.RoleUpdateDTO;
import cn.boot.st.system.vo.RoleVO;

import javax.validation.Valid;
import java.util.List;

/**
 * @Classname RoleService
 * @Description 角色
 * @Date 2020/12/5 17:59
 * @Created by maht
 */
public interface RoleService {

    /**
     * 创建角色
     *
     * @param roleCreateDTO
     * @param createAdminId
     * @return
     */
    Integer createRole(RoleCreateDTO roleCreateDTO, Integer createAdminId);


    /**
     * 修改角色
     *
     * @param updateDTO
     */
    void updateRole(@Valid RoleUpdateDTO updateDTO);


    /**
     * 删除角色
     *
     * @param roleId
     */
    void deleteRole(Integer roleId);

    /**
     * 获取角色信息
     *
     * @param roleId
     * @return
     */
    RoleVO getRole(Integer roleId);

    /**
     * 获取所有角色
     *
     * @return
     */
    List<RoleVO> listAllRoles();

    /**
     * 角色列表
     *
     * @param roleIds
     * @return
     */
    List<RoleVO> listRoles(List<Integer> roleIds);

    PageResult<RoleVO> pageRole(RolePageDTO rolePageDTO);

    /**
     * 获取用户角色信息
     * @param userId
     * @return
     */
    List<Integer> getUserRoleInfo(Integer userId);


}
