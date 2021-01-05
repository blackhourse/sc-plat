package cn.boot.st.gateway.remote.faback;

import cn.boot.common.framework.exception.util.GlobalException;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.gateway.remote.ManageWebService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static cn.boot.common.framework.constant.SystemErrorCodeConstants.SERVICE_DEGRADATION;

/**
 * @Classname ManageWebServiceFallback
 * @Description
 * @Date 2021/1/5
 * @Created by maht
 */
@Slf4j
public class ManageWebServiceFallback implements ManageWebService {
    private Throwable throwable;

    ManageWebServiceFallback(Throwable throwable) {
        this.throwable = throwable;
    }


    @Override
    public CommonResult<List<Integer>> getInfoById(Integer userId) {
        log.error("服务降级[userId:{}]", userId);
        return CommonResult.error(new GlobalException(SERVICE_DEGRADATION));
    }
}
