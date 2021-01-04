package cn.boot.st.managementweb.service.login;

import cn.boot.st.managementweb.controller.login.dto.PassportLoginDTO;
import cn.boot.common.framework.dataobject.vo.PassportAccessTokenVO;
import cn.boot.st.managementweb.controller.login.vo.PassportAdminVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname LoginService
 * @Description
 * @Date 2020/12/6
 * @Created by maht
 */
public interface LoginService {
    /**
     * 账号密码登录
     *
     * @param loginDTO
     * @param request
     * @return
     */
    PassportAccessTokenVO login(PassportLoginDTO loginDTO, HttpServletRequest request);

    /**
     * 获取当前管理员信息
     *
     * @param adminId
     * @return
     */
    PassportAdminVO getAdminInfo(Integer adminId);

}
