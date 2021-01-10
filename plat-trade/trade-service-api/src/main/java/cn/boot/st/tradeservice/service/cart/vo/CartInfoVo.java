package cn.boot.st.tradeservice.service.cart.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-07
 **/
@Data
@Accessors(chain = true)
public class CartInfoVo {


    private Fee fee;

    private List<Sku> skuList;

    /**
     * 费用（合计）
     */
    @Data
    @Accessors(chain = true)
    public static class Sku {
        /**
         * sku 编号
         */
        private Integer id;
        /**
         * 商品编号
         */
        private Integer spuId;
        /**
         * 状态
         * <p>
         * 1-正常
         * 2-禁用
         */
        private Integer status;
        /**
         * 图片地址
         */
        private String picUrl;
        /**
         * 规格值编号数组
         */
        private List<AttrValueInfo> attrValueIds;
        /**
         * 价格，单位：分
         */
        private Integer price;
        /**
         * 库存数量
         */
        private Integer quantity;

        /**
         * 是否选中
         */
        private Boolean selected;

        private Spu spu;

    }

    @Data
    @Accessors(chain = true)
    public static class Spu {

        /**
         * SPU 编号
         */
        private Integer id;

        // ========== 基本信息 =========
        /**
         * SPU 名字
         */
        private String name;
        /**
         * 分类编号
         */
        private Integer cid;
        /**
         * 商品主图地址
         * <p>
         * 数组，以逗号分隔
         * <p>
         * 建议尺寸：800*800像素，你可以拖拽图片调整顺序，最多上传15张
         */
        private List<String> picUrls;

    }


    /**
     * 属性值
     */
    @Data
    @Accessors(chain = true)
    public static class AttrValueInfo {
        /**
         * 属性valueId id
         */
        private Integer attrValueId;
        /**
         * 是否选中
         */
        private Boolean isSelect;
    }

    /**
     * 费用（合计）
     */
    @Data
    @Accessors(chain = true)
    public static class Fee {

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
}
