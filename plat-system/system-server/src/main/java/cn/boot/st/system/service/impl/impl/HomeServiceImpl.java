package cn.boot.st.system.service.impl.impl;

import cn.boot.st.redis.RedisService;
import cn.boot.st.system.dto.home.VisitReqDTO;
import cn.boot.st.system.service.HomeService;
import cn.boot.st.system.vo.home.HomePvVo;
import cn.boot.st.system.vo.home.RankDO;
import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-02-02
 **/
@Service
public class HomeServiceImpl implements HomeService {

    private static final String RANK_PREFIX = "global_rank";

    @Autowired
    private RedisService redisService;

    @Override
    public HomePvVo addPv(VisitReqDTO reqDTO) {
        String pvKey = buildPvKey(reqDTO.getApp());
        String todayPvKey = buildTodayPvKey(reqDTO.getApp());
        // 获取pv
        Object pvObj = redisService.hget(pvKey, reqDTO.getUri());
        Long totalPv = 0L;
        if (pvObj == null) {
            totalPv = 1L;
        } else {
            totalPv = Long.valueOf(totalPv + String.valueOf(pvObj));
        }
        // 访问一次 pv + 1
        redisService.hincr(pvKey, reqDTO.getUri(), 1);

        Long todayPv = 0L;

        Object todayPvObj = redisService.hget(todayPvKey, reqDTO.getUri());
        if (todayPvObj == null) {
            todayPv = 1L;
        } else {
            todayPv = Long.valueOf(todayPv + String.valueOf(todayPvObj));
        }
        // 今天pv + 1
        redisService.hincr(todayPvKey, reqDTO.getUri(), 1);
        // uv
        String buildTodayUvKey = buildTodayUvKey(reqDTO.getApp(), reqDTO.getUri());
        redisService.addHyperLogLogOperations(buildTodayUvKey, reqDTO.getIp());
        Long todayUvSize = redisService.getHyperLogLogOperationsSize(buildTodayUvKey);
        String buildUvKey = buildUvKey(reqDTO.getApp(), reqDTO.getUri());
        redisService.addHyperLogLogOperations(buildUvKey, reqDTO.getIp());
        Long uvSize = redisService.getHyperLogLogOperationsSize(buildUvKey);
        return new HomePvVo().setTotalPv(totalPv).setTodayPv(todayPv)
                .setTodayUv(todayUvSize).setTotalUv(uvSize);
    }

    private List<RankDO> buildRedisRankToBizDO(Set<ZSetOperations.TypedTuple<Object>> result, long offset) {
        List<RankDO> rankList = new ArrayList<>(result.size());
        long rank = offset;
        for (ZSetOperations.TypedTuple<Object> sub : result) {
            rankList.add(new RankDO(rank++, Math.abs(sub.getScore().floatValue()), Long.parseLong(String.valueOf(sub.getValue()))));
        }
        return rankList;
    }

    /**
     * 获取前n名的排行榜数据
     *
     * @param n
     * @return
     */
    @Override
    public List<RankDO> getTopNRanks(int n) {
        Set<ZSetOperations.TypedTuple<Object>> result = redisService.zRangeWithScore(RANK_PREFIX, 0, n - 1);
        return buildRedisRankToBizDO(result, 1);
    }


    /**
     * 获取用户所在排行榜的位置，以及排行榜中其前后n个用户的排行信息
     *
     * @param userId
     * @param n
     * @return
     */
    @Override
    public List<RankDO> getRankAroundUser(Long userId, int n) {
        // 首先是获取用户对应的排名
        RankDO rank = getRank(userId);
        if (rank.getRank() <= 0) {
            // fixme 用户没有上榜时，不返回
            return Collections.emptyList();
        }

        // 因为实际的排名是从0开始的，所以查询周边排名时，需要将n-1
        Set<ZSetOperations.TypedTuple<Object>> result =
                redisService.zRangeWithScore(RANK_PREFIX, (int) Math.max(0, rank.getRank() - n - 1), rank.getRank().intValue() + n - 1);
        return buildRedisRankToBizDO(result, Math.max(1, rank.getRank().longValue() - n));
    }


    /**
     * 获取用户的排行榜位置
     *
     * @param userId
     * @return
     */
    @Override
    public RankDO getRank(Long userId) {
        // 获取排行， 因为默认是0为开头，因此实际的排名需要+1
        Long rank = redisService.rank(RANK_PREFIX, String.valueOf(userId));
        if (rank == null) {
            // 没有排行时，直接返回一个默认的
            return new RankDO(-1L, 0F, userId);
        }

        // 获取积分
        Long score = redisService.zScore(RANK_PREFIX, String.valueOf(userId));
        return new RankDO(rank + 1, Math.abs(score.floatValue()), userId);
    }

    /**
     * 更新用户积分，并获取最新的个人所在排行榜信息
     *
     * @param userId
     * @param score
     * @return
     */
    @Override
    public RankDO updateRank(Long userId, Float score) {
        // 因为zset默认积分小的在前面，所以我们对score进行取反，这样用户的积分越大，对应的score越小，排名越高
        redisService.zAdd(RANK_PREFIX, String.valueOf(userId), -score);
        Long rank = redisService.rank(RANK_PREFIX, String.valueOf(userId));
        return new RankDO(rank + 1, score, userId);
    }

    /**
     * 应用的pv统计计数
     *
     * @param app
     * @return
     */
    private String buildTodayPvKey(String app) {
        String date = DateUtil.format(new Date(), "yyyy-MM-dd");
        return "site_cnt_" + app + "_" + date;
    }

    private String buildPvKey(String app) {
        return "site_cnt_" + app;
    }

    private String buildUvKey(String app, String uri) {
        return "uri_rank_" + app + "_" + uri;
    }

    private String buildTodayUvKey(String app, String uri) {
        String date = DateUtil.format(new Date(), "yyyy-MM-dd");
        return date + "_" + "uri_rank_" + app + "_" + uri;
    }


}
