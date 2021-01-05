package cn.boot.st.gateway.remote;

import cn.boot.common.framework.constant.ServiceNameConstants;
import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.gateway.remote.faback.ManageWebServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-04
 **/
@FeignClient(name = ServiceNameConstants.MANAGEMENT_WEB_SERVICE, fallbackFactory = ManageWebServiceFallbackFactory.class)
public interface ManageWebService {

    @GetMapping("/api/role/get")
    public CommonResult<List<Integer>> getInfoById(@RequestParam("userId") Integer userId);


}
