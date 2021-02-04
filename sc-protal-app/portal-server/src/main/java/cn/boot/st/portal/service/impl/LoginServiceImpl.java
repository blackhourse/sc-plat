package cn.boot.st.portal.service.impl;

import cn.boot.common.framework.constant.PortalConstants;
import cn.boot.common.framework.exception.util.ServiceException;
import cn.boot.st.portal.convert.UmsMemberConvert;
import cn.boot.st.portal.mapper.UmsMember;
import cn.boot.st.portal.mapper.UmsMemberMapper;
import cn.boot.st.portal.service.LoginService;
import cn.boot.st.portal.vo.UmsLoginCodeVo;
import cn.boot.st.portal.vo.UmsMemberLoginVo;
import cn.boot.st.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Random;

import static cn.boot.st.portal.constants.TradeErrorCodeConstants.*;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-27
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisService redisService;

    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Override
    public UmsLoginCodeVo getMobileCode(String phone) {

        long expireTime = redisService.getExpire(PortalConstants.APP_LOGIN_SMS_CODE_KEY + phone);
        if (expireTime > 0) {
            return new UmsLoginCodeVo().setReamingTime(Math.toIntExact(expireTime));
        }

        int code = new Random().nextInt(9999);
        redisService.set(PortalConstants.APP_LOGIN_SMS_CODE_KEY + phone, code, PortalConstants.APP_LOGIN_SMS_CODE_TIME);
        return new UmsLoginCodeVo().setCode(code).setReamingTime(PortalConstants.APP_LOGIN_SMS_CODE_TIME);
    }

    @Override
    public UmsMemberLoginVo loginByPhoneCode(String phone, Integer code) {
        // 验证码为空
        if (Objects.isNull(code)) {
            throw new ServiceException(PARAM_PHONE_CODE_NULL);
        }
        Object codeFromRedis = redisService.get(PortalConstants.APP_LOGIN_SMS_CODE_KEY + phone);
        // 验证码过期
        if (codeFromRedis == null) {
            throw new ServiceException(REDIS_PHONE_CODE_EXPIRED);
        }
        if (codeFromRedis == code) {
            throw new ServiceException(REDIS_PHONE_CODE_ERROR);
        }
        UmsMember umsMember = umsMemberMapper.selectOneByPhone(phone);
        if (Objects.isNull(umsMember)) {
            throw new ServiceException(MEMBER_NOT_EXIST);
        }
        UmsMemberLoginVo memberLoginVo = UmsMemberConvert.INSTANCE.convert(umsMember);
        redisService.del(PortalConstants.APP_LOGIN_SMS_CODE_KEY + phone);
        return memberLoginVo;
    }
}
