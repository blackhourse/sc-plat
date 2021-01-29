package cn.boot.st.portal.convert;

import cn.boot.st.portal.dataobject.UmsMember;
import cn.boot.st.portal.vo.UmsMemberLoginVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Classname CartConvert
 * @Description
 * @Date 2021/1/8
 * @Created by maht
 */
@Mapper
public interface UmsMemberConvert {
    UmsMemberConvert INSTANCE = Mappers.getMapper(UmsMemberConvert.class);

    UmsMemberLoginVo convert(UmsMember umsMember);

}
