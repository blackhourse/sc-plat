package cn.boot.st.managementweb.service;

import cn.boot.st.managementweb.dataobject.dto.PassportLoginDTO;
import cn.boot.st.managementweb.dataobject.vo.PassportAccessTokenVO;

/**
 * @Classname LoginService
 * @Description
 * @Date 2020/12/6
 * @Created by maht
 */
public interface LoginService {
    /**
     * 账号密码登录
     * @param loginDTO
     * @param ip
     * @return
     */
    PassportAccessTokenVO login(PassportLoginDTO loginDTO, String ip);

}
