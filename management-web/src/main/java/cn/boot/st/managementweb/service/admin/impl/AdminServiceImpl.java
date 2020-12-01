package cn.boot.st.managementweb.service.admin.impl;

import cn.boot.st.managementweb.mapper.admin.AdminMapper;
import cn.boot.st.managementweb.mapper.admin.DepartmentMapper;
import cn.boot.st.managementweb.mysql.dataobject.admin.AdminDO;
import cn.boot.st.managementweb.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public AdminDO getById(Integer id) {
        departmentMapper.selectById(1);

        return adminMapper.selectById(id);
    }
}
