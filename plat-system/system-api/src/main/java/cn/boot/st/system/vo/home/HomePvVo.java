package cn.boot.st.system.vo.home;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-02-03
 **/
@Data
@Accessors(chain = true)
public class HomePvVo {
    private Long totalPv;
    private Long todayPv;

    private Long todayUv;
    private Long totalUv;


}
