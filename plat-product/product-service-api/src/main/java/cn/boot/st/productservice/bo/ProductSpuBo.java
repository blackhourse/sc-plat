package cn.boot.st.productservice.bo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-07
 **/
@Data
@Accessors(chain = true)
public class ProductSpuBo {
    /**
     * SPU 编号
     */
    private Integer id;
    /**
     * SPU 名字
     */
    private String name;
    /**
     * 卖点
     */
    private String sellPoint;
    /**
     * 描述
     */
    private String description;
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
    private String picUrls;
    /**
     * 是否上架商品（是否可见）。
     * <p>
     * true 为已上架
     * false 为已下架
     */
    private Integer visible;
    /**
     * 排序字段
     */
    private Integer sort;
    /**
     * 价格
     */
    private Integer price;
    /**
     * 库存数量
     */
    private Integer quantity;

}
