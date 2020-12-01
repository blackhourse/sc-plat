package cn.boot.st.managementweb.service.admin;

import cn.boot.st.managementweb.mysql.dataobject.admin.AdminDO;

public interface AdminService {

    AdminDO getById(Integer id);

}
