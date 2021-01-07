package cn.boot.st.productserver.convert;

import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.productserver.dataobject.ProductAttr;
import cn.boot.st.productserver.dataobject.ProductAttrValue;
import cn.boot.st.productservice.dto.attr.ProductAttrKeyCreateDTO;
import cn.boot.st.productservice.dto.attr.ProductAttrKeyUpdateDTO;
import cn.boot.st.productservice.dto.attr.ProductAttrValueCreateDTO;
import cn.boot.st.productservice.dto.attr.ProductAttrValueUpdateDTO;
import cn.boot.st.productservice.vo.attr.ProductAttrKeyVO;
import cn.boot.st.productservice.vo.attr.ProductAttrValueRespVO;
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
