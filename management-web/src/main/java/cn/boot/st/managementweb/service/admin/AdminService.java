package cn.boot.st.managementweb.service.admin;

import cn.boot.st.managementweb.dataobject.admin.AdminDO;
import cn.boot.st.managementweb.dataobject.dto.AdminCreateDTO;

public interface AdminService {

    AdminDO getById(Integer id);

    Integer createAdmin(AdminCreateDTO createDTO);

}
