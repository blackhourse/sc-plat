package cn.boot.st.system.service;

import cn.boot.st.system.dto.home.VisitReqDTO;
import cn.boot.st.system.vo.home.HomePvVo;
import cn.boot.st.system.vo.home.RankDO;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-02-01
 **/
public interface HomeService {

    /**
     * @param visitReqDTO
     * @return
     */
    HomePvVo addPv(VisitReqDTO visitReqDTO);

    /**
     * 更新用户积分，并获取最新的个人所在排行榜信息
     *
     * @param userId
     * @param score
     * @return
     */
    RankDO updateRank(Long userId, Float score);

    /**
     * 获取用户的排行榜位置
     *
     * @param userId
     * @return
     */
    RankDO getRank(Long userId);

    /**
     * 获取前n名的排行榜数据
     *
     * @param n
     * @return
     */
    List<RankDO> getTopNRanks(int n);

    /**
     * 获取用户所在排行榜的位置，以及排行榜中其前后n个用户的排行信息
     *
     * @param userId
     * @param n
     * @return
     */
    List<RankDO> getRankAroundUser(Long userId, int n);
}
