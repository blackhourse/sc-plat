package cn.boot.st.system.controller;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.system.dto.home.VisitReqDTO;
import cn.boot.st.system.service.HomeService;
import cn.boot.st.system.vo.home.HomePvVo;
import cn.boot.st.system.vo.home.RankDO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-02-01
 **/
@RestController
@RequestMapping("/index")
@Api(tags = "首页")
public class HomeController {


    @Autowired
    private HomeService homeService;


    @PostMapping(path = "addPv")
    @ResponseBody
    public HomePvVo addPv(VisitReqDTO reqDTO) {
        return homeService.addPv(reqDTO);
    }

    @GetMapping("test")
    public void test() {
        Random random = new Random();
        for (int i = 1; i < 100; i++) {
            Integer userId = random.nextInt(1024);
            Double score = random.nextDouble() * 100;
            homeService.updateRank(userId.longValue(), score.floatValue());
        }
    }

    @GetMapping("updateRank")
    public void updateRank(Long userId, Float score) {
        homeService.updateRank(userId, score);
    }

    @GetMapping("getRankAroundUser")
    public List<RankDO> getRankAroundUser(Long userId, int n) {
        return homeService.getRankAroundUser(userId, n);
    }

    @GetMapping("topn-score")
    public CommonResult<List<RankDO>> top10Score(Integer n) {
        return CommonResult.success(homeService.getTopNRanks(n));
    }

}
