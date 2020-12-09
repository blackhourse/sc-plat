package cn.boot.st.web.handler;

import cn.boot.common.framework.exception.util.GlobalException;
import cn.boot.common.framework.exception.util.ServiceException;
import cn.boot.common.framework.vo.CommonResult;
import cn.hutool.core.exceptions.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import static cn.boot.common.framework.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST;
import static cn.boot.common.framework.exception.enums.GlobalErrorCodeConstants.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Value("${spring.application.name}")
    private String applicationName;


    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public CommonResult missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException ex) {
        logger.warn("[missingServletRequestParameterExceptionHandler]", ex);
        return CommonResult.error(BAD_REQUEST.getCode(), String.format("请求参数缺失:%s", ex.getParameterName()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理业务异常 ServiceException
     * <p>
     * 例如说，商品库存不足，用户手机号已存在。
     */
    @ExceptionHandler(value = ServiceException.class)
    public CommonResult serviceExceptionHandler(ServiceException ex) {
        logger.info("[serviceExceptionHandler]", ex);
        return CommonResult.error(ex.getCode(), ex.getMessage());
    }


    /**
     * 处理 SpringMVC 参数绑定不正确，本质上也是通过 Validator 校验
     */
    @ExceptionHandler(BindException.class)
    public CommonResult bindExceptionHandler(BindException ex) {
        logger.warn("[handleBindException]", ex);
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null; // 断言，避免告警
        return CommonResult.error(BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", fieldError.getDefaultMessage()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理 Validator 校验不通过产生的异常
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public CommonResult constraintViolationExceptionHandler(ConstraintViolationException ex) {
        logger.warn("[constraintViolationExceptionHandler]", ex);
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        return CommonResult.error(BAD_REQUEST.getCode(), String.format("请求参数不正确:%s", constraintViolation.getMessage()))
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }


    /**
     * 处理本地参数校验时，抛出的 ValidationException 异常
     */
    @ExceptionHandler(value = ValidationException.class)
    public CommonResult validationException(ValidationException ex) {
        logger.warn("[constraintViolationExceptionHandler]", ex);
        return CommonResult.error(BAD_REQUEST.getCode(), "请求参数不正确")
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

    /**
     * 处理全局异常 ServiceException  状态为401 402 403 等
     * <p>
     */
    @ExceptionHandler(value = GlobalException.class)
    public CommonResult globalExceptionHandler(HttpServletRequest req, GlobalException ex) {
        // 系统异常时，才打印异常日志
        if (INTERNAL_SERVER_ERROR.getCode().equals(ex.getCode())) {
            // todo 插入异常日志
            // 普通全局异常，打印 info 日志即可
        } else {
            logger.info("[globalExceptionHandler]", ex);
        }
        // 返回 ERROR CommonResult
        return CommonResult.error(ex);
    }

    /**
     * 处理系统异常，兜底处理所有的一切
     */
    @ExceptionHandler(value = Exception.class)
    public CommonResult defaultExceptionHandler(HttpServletRequest req, Throwable ex) {
        logger.error("[defaultExceptionHandler]", ex);
        // 插入异常日志
        // 返回 ERROR CommonResult
        return CommonResult.error(INTERNAL_SERVER_ERROR.getCode(), INTERNAL_SERVER_ERROR.getMessage())
                .setDetailMessage(ExceptionUtil.getRootCauseMessage(ex));
    }

}
