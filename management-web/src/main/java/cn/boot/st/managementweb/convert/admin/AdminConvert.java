package cn.boot.st.managementweb.convert.admin;

import cn.boot.st.managementweb.dataobject.admin.AdminDO;
import cn.boot.st.managementweb.dataobject.dto.AdminCreateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminConvert {

    AdminConvert INSTANCE = Mappers.getMapper(AdminConvert.class);

    AdminDO convert(AdminCreateDTO bean);


}
