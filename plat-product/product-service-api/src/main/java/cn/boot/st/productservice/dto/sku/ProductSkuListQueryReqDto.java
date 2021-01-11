package cn.boot.st.productservice.dto.sku;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2021-01-11
 **/

import cn.boot.st.productservice.enums.ProductSkuDetailFieldEnum;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collection;
import java.util.Collections;

@Data
@Accessors(chain = true)
@ApiModel("商品 SKU 列表查询 DTO")
public class ProductSkuListQueryReqDto {


    /**
     * 商品 SKU 编号数组
     */
    private Collection<Integer> productSkuIds;

    /**
     * 额外返回字段
     *
     * @see ProductSkuDetailFieldEnum
     */
    private Collection<String> fields = Collections.emptySet();

}
