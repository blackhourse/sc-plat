package cn.boot.st.product.convert;

import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.product.controller.attr.dto.ProductAttrKeyCreateDTO;
import cn.boot.st.product.controller.attr.dto.ProductAttrKeyUpdateDTO;
import cn.boot.st.product.controller.attr.dto.ProductAttrValueCreateDTO;
import cn.boot.st.product.controller.attr.dto.ProductAttrValueUpdateDTO;
import cn.boot.st.product.controller.attr.vo.ProductAttrKeyVO;
import cn.boot.st.product.controller.attr.vo.ProductAttrValueRespVO;
import cn.boot.st.product.dataobject.domain.ProductAttr;
import cn.boot.st.product.dataobject.domain.ProductAttrValue;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @program: sc-plat
 * @author: maht
 * @create: 2020-12-14
 **/
@Mapper
public interface ProductAttrConvert {

    ProductAttrConvert INSTANCE = Mappers.getMapper(ProductAttrConvert.class);

    ProductAttr convert(ProductAttrKeyCreateDTO dto);

    ProductAttrKeyVO convert(ProductAttr productAttr);

    ProductAttr convert(ProductAttrKeyUpdateDTO productAttrKeyUpdateDTO);


    ProductAttrValue convert(ProductAttrValueCreateDTO productAttrValueCreateDTO);

    @Mapping(target = "list", source = "records")
    PageResult<ProductAttrKeyVO> convertPage(IPage<ProductAttr> productAttrIPage);

    ProductAttrValue convert(ProductAttrValueUpdateDTO productAttrValueUpdateDTO);

    ProductAttrValueRespVO convert(ProductAttrValue productAttrValue);

    List<ProductAttrValueRespVO> convert(List<ProductAttrValue> productAttrValueList);


}
