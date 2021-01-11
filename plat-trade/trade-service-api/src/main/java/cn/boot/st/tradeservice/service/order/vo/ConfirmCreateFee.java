package cn.boot.st.tradeservice.service.order.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-11
 **/
@Data
@Accessors(chain = true)
public class ConfirmCreateFee {
    /**
     * 购买总价
     */
    private Integer buyTotal;
    /**
     * 优惠总价
     * <p>
     * 注意，满多少元包邮，不算在优惠中。
     */
    private Integer discountTotal;
    /**
     * 邮费
     */
    private Integer postageTotal;
    /**
     * 最终价格
     * <p>
     * 计算公式 = 总价 - 优惠总价 + 邮费
     */
    private Integer presentTotal;

}
