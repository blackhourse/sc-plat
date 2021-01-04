package cn.boot.st.managementweb.service.login.impl;

import cn.boot.common.framework.dataobject.dto.OAuth2AccessTokenRespDTO;
import cn.boot.common.framework.dataobject.dto.OAuth2CreateAccessTokenReqDTO;
import cn.boot.common.framework.dataobject.vo.AdminUserTokenInfo;
import cn.boot.common.framework.dataobject.vo.PassportAccessTokenVO;
import cn.boot.common.framework.enums.admin.AdminStatusEnum;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.common.framework.util.DigestUtils;
import cn.boot.common.framework.util.HttpUtil;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.managementweb.controller.login.dto.PassportLoginDTO;
import cn.boot.st.managementweb.controller.login.vo.PassportAdminVO;
import cn.boot.st.managementweb.convert.admin.AdminConvert;
import cn.boot.st.managementweb.dataobject.domain.AdminDO;
import cn.boot.st.managementweb.mapper.admin.AdminMapper;
import cn.boot.st.managementweb.remote.AuthFeignService;
import cn.boot.st.managementweb.service.admin.AdminCacheService;
import cn.boot.st.managementweb.service.login.LoginService;
import cn.boot.st.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static cn.boot.common.framework.constant.SystemErrorCodeConstants.*;
import static cn.boot.common.framework.enums.UserTypeEnum.ADMIN;

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

    @Autowired
    private AuthFeignService authFeignService;

    @Resource
    private RedisService redisService;

    @Autowired
    private AdminCacheService adminCacheService;

    @Override
    public PassportAccessTokenVO login(PassportLoginDTO loginDTO, HttpServletRequest request) {
        String ip = HttpUtil.getIp(request);
        // 验证用户名，密码
        AdminDO adminDO = verifyPassword(loginDTO.getUsername(), loginDTO.getPassword(), ip);
        OAuth2CreateAccessTokenReqDTO oAuth2CreateAccessTokenReqDTO = new OAuth2CreateAccessTokenReqDTO().setUserId(adminDO.getId())
                .setUserType(ADMIN.getValue()).setCreateIp(ip);
        CommonResult<OAuth2AccessTokenRespDTO> commonResult = authFeignService.createAccessToken(oAuth2CreateAccessTokenReqDTO, request);
        commonResult.checkError();
        // 用户信息
        AdminUserTokenInfo adminUserTokenInfo = AdminConvert.INSTANCE.convertTokenInfo(adminDO);
        OAuth2AccessTokenRespDTO tokenResult = commonResult.getData();
        adminUserTokenInfo.setAccessToken(tokenResult.getAccessToken()).setRefreshToken(tokenResult.getRefreshToken());
        adminCacheService.setAuthToken(tokenResult.getAccessToken(), adminUserTokenInfo);
        return new PassportAccessTokenVO().setAccessToken(tokenResult.getAccessToken()).setRefreshToken(tokenResult.getRefreshToken())
                .setExpiresTime(tokenResult.getExpiresTime());
    }

    @Override
    public PassportAdminVO getAdminInfo(Integer adminId) {
        AdminDO adminDO = adminMapper.selectById(adminId);
        return AdminConvert.INSTANCE.convert(adminDO);
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
            // TODO 密码错误超过5次，当天锁定账户
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
