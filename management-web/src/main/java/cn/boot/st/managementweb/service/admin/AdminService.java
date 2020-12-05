package cn.boot.st.managementweb.service.admin;

import cn.boot.st.managementweb.dataobject.domain.AdminDO;
import cn.boot.st.managementweb.dataobject.dto.AdminCreateDTO;
import cn.boot.st.managementweb.dataobject.dto.AdminUpdateInfoDTO;
import cn.boot.st.managementweb.dataobject.dto.AdminUpdateStatusDTO;

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

}
