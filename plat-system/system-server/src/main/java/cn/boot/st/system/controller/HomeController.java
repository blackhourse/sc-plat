package cn.boot.st.system.controller;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.system.dto.home.VisitReqDTO;
import cn.boot.st.system.service.HomeService;
import cn.boot.st.system.vo.home.SiteVisitDTO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-02-01
 **/
@RestController
@RequestMapping("/index")
@Api(tags = "首页")
public class HomeController {

//    SiteVisitDTO visit(VisitReqDTO reqDTO);

    @Autowired
    private HomeService homeService;

    @PostMapping("add")
    public CommonResult<SiteVisitDTO> add(VisitReqDTO reqDTO) {
        return CommonResult.success(homeService.add(reqDTO));
    }
}
