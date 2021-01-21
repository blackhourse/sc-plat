package cn.boot.st.system.service;



import cn.boot.st.system.dataobject.AdminDO;
import cn.boot.st.system.dto.AdminCreateDTO;
import cn.boot.st.system.dto.AdminUpdateInfoDTO;
import cn.boot.st.system.dto.AdminUpdateStatusDTO;
import cn.boot.st.system.vo.UserVo;

import java.util.Collection;

public interface AdminService {

    AdminDO getById(Integer id);

    Integer createAdmin(AdminCreateDTO createDTO);

    /**
     * 更新管理员
     *
     * @param adminUpdateInfoDTO
     */
    void updateAdmin(AdminUpdateInfoDTO adminUpdateInfoDTO);

    /**
     * 更新管理员状态
     *
     * @param adminUpdateStatusDTO
     */
    void updateAdminStatus(AdminUpdateStatusDTO adminUpdateStatusDTO);

    /**
     * 用户是否拥有管理员角色
     * @param roleIds
     * @return
     */
    Boolean hasSuperAdmin(Collection<Integer> roleIds);

    UserVo getUserInfoByUserName(String userName);

}
