package cn.boot.st.managementweb.service.impl;

import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.util.DigestUtils;
import cn.boot.st.managementweb.dataobject.domain.AdminDO;
import cn.boot.st.managementweb.dataobject.dto.PassportLoginDTO;
import cn.boot.st.managementweb.dataobject.vo.PassportAccessTokenVO;
import cn.boot.common.framework.enums.admin.AdminStatusEnum;
import cn.boot.st.managementweb.mapper.admin.AdminMapper;
import cn.boot.st.managementweb.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.boot.common.framework.constant.SystemErrorCodeConstants.*;

/**
 * @Classname LoginServiceImpl
 * @Description
 * @Date 2020/12/6
 * @Created by maht
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public PassportAccessTokenVO login(PassportLoginDTO loginDTO, String ip) {

        AdminDO adminDO = verifyPassword(loginDTO.getUsername(), loginDTO.getPassword(), ip);
        return null;
    }


    /**
     * 校验登陆的账号密码是否正确
     *
     * @param username 账号
     * @param password 密码
     * @param ip       登陆 IP
     * @return 管理员信息
     */
    public AdminDO verifyPassword(String username, String password, String ip) {
        AdminDO adminDO = adminMapper.selectByUsername(username);
        if (adminDO == null) {
            throw ServiceExceptionUtil.exception(ADMIN_USERNAME_NOT_EXISTS);
        }
        // 校验密码是否正确
        String encodedPassword = DigestUtils.bcrypt(password, adminDO.getPasswordSalt());
        if (!encodedPassword.equals(adminDO.getPassword())) {
            // TODO 需要补充密码错误上限
            throw ServiceExceptionUtil.exception(ADMIN_PASSWORD_ERROR);
        }
        // 账号被禁用
        if (!AdminStatusEnum.ACTIVE.getStatus().equals(adminDO.getStatus())) {
            throw ServiceExceptionUtil.exception(ADMIN_IS_DISABLE);
        }
        // 返回
        return adminDO;
    }
}
