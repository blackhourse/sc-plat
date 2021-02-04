package cn.boot.st.portal.service;

import cn.boot.st.portal.vo.UmsLoginCodeVo;
import cn.boot.st.portal.vo.UmsMemberLoginVo;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-27
 **/
public interface LoginService {

    /**
     * 获取短信验证码
     * @param phone
     * @return
     */
    UmsLoginCodeVo getMobileCode(String phone);

    /**
     * 手机号验证码登录
     * @param phone
     * @param code
     * @return
     */
    UmsMemberLoginVo loginByPhoneCode(String phone, Integer code);

}
