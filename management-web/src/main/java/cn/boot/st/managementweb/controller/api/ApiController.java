package cn.boot.st.managementweb.controller.api;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.managementweb.service.api.ApiService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.boot.common.framework.vo.CommonResult.success;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-31
 **/
@RestController
@RequestMapping("api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/role/get")
    @ApiOperation("获得用户角色")
    @ApiImplicitParam(name = "userId", value = "userId", required = true)
    public CommonResult<List<Integer>> role(@RequestParam("userId") Integer userId) {
        return success(apiService.getUserRoleInfo(userId));
    }


}
