package cn.boot.st.securityserver.manager;

import cn.boot.common.framework.dataobject.dto.OAuth2AccessTokenRespDTO;
import cn.boot.st.securityserver.service.manager.ManagerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname AdminTokenController
 * @Description
 * @Date 2020/12/7
 * @Created by maht
 */
@Api(tags = "对外提供认证 API")
@RestController
@RequestMapping("/auth")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/id-info")
    @ApiOperation("根据id获取详情")
    public OAuth2AccessTokenRespDTO getInfoById(String token) {
        return managerService.getInfoById(token);
    }


}
