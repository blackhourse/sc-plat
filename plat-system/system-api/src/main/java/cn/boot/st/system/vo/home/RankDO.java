package cn.boot.st.system.vo.home;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-02-02
 **/
@Data
@AllArgsConstructor
public class RankDO {
    /**
     * 排名
     */
    private Long rank;

    /**
     * 积分
     */
    private Float score;

    /**
     * 用户id
     */
    private Long userId;
}
