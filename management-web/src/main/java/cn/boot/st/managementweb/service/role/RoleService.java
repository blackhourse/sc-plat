package cn.boot.st.managementweb.service.role;

import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.managementweb.dataobject.domain.RoleDO;
import cn.boot.st.managementweb.dataobject.dto.RoleCreateDTO;
import cn.boot.st.managementweb.dataobject.dto.RolePageDTO;
import cn.boot.st.managementweb.dataobject.dto.RoleUpdateDTO;
import cn.boot.st.managementweb.dataobject.vo.RoleVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

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
     * @param roleId
     */
    void deleteRole(Integer roleId);

    /**
     * 获取角色信息
     * @param roleId
     * @return
     */
    RoleVO getRole(Integer roleId);

    /**
     * 获取所有角色
     * @return
     */
    List<RoleVO> listAllRoles();

    /**
     * 角色列表
     * @param roleIds
     * @return
     */
    List<RoleVO> listRoles(List<Integer> roleIds);

    PageResult<RoleVO> pageRole(RolePageDTO rolePageDTO);

}
