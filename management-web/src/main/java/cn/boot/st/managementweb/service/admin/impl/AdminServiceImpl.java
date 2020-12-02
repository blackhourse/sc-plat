package cn.boot.st.managementweb.service.admin.impl;

import cn.boot.common.framework.enums.CommonStatusEnum;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.util.DigestUtils;
import cn.boot.st.managementweb.convert.admin.AdminConvert;
import cn.boot.st.managementweb.dataobject.admin.AdminDO;
import cn.boot.st.managementweb.dataobject.dto.AdminCreateDTO;
import cn.boot.st.managementweb.mapper.admin.AdminMapper;
import cn.boot.st.managementweb.mapper.admin.DepartmentMapper;
import cn.boot.st.managementweb.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.boot.st.managementweb.enums.SystemErrorCodeConstants.ADMIN_USERNAME_EXISTS;

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

    @Override
    public Integer createAdmin(AdminCreateDTO createDTO) {
        // 校验账号唯一
        if (adminMapper.selectByUsername(createDTO.getUsername()) != null) {
            throw ServiceExceptionUtil.exception(ADMIN_USERNAME_EXISTS);
        }

        // 加密密码
        String passwordSalt = genPasswordSalt();
        String password = encodePassword(createDTO.getPassword(), passwordSalt);
        // 保存到数据库
        AdminDO admin = AdminConvert.INSTANCE.convert(createDTO)
                .setPassword(password).setPasswordSalt(passwordSalt)
                .setStatus(CommonStatusEnum.ENABLE.getValue());
        adminMapper.insert(admin);
        return admin.getId();
    }

    private String genPasswordSalt() {
        return DigestUtils.genBcryptSalt();
    }

    private String encodePassword(String password, String salt) {
        return DigestUtils.bcrypt(password, salt);
    }


}
