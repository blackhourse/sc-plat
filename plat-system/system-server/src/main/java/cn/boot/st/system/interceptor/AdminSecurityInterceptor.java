package cn.boot.st.system.interceptor;


import cn.boot.common.framework.constant.AuthConstants;
import cn.boot.common.framework.util.StringUtils;
import cn.boot.st.security.core.context.AdminSecurityContext;
import cn.boot.st.security.core.context.AdminSecurityContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 */
@Slf4j
public class AdminSecurityInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String adminId = request.getHeader(AuthConstants.JWT_USER_ID_KEY);
        if (StringUtils.hasText(adminId)) {
            AdminSecurityContext adminSecurityContext = new AdminSecurityContext().setAdminId(Integer.valueOf(adminId));
            AdminSecurityContextHolder.setContext(adminSecurityContext);
        }
        return true;
    }


}
