package cn.boot.st.securityserver.exception;


import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.securityserver.constants.SecurityServerConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AuthExceptionHandler {

    /**
     * 用户名和密码异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(InvalidGrantException.class)
    public CommonResult handleInvalidGrantException(InvalidGrantException e) {
        return CommonResult.error(SecurityServerConstants.USERNAME_OR_PASSWORD_ERROR.getCode(),
                SecurityServerConstants.USERNAME_OR_PASSWORD_ERROR.getMessage());
    }


    /**
     * 账户异常(禁用、锁定、过期)
     *
     * @param e
     * @return
     */
    @ExceptionHandler({InternalAuthenticationServiceException.class})
    public CommonResult handleInternalAuthenticationServiceException(InternalAuthenticationServiceException e) {
        return CommonResult.error(e.getMessage());
    }

}
