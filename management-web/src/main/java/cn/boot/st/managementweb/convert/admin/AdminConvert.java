package cn.boot.st.managementweb.convert.admin;

import cn.boot.st.managementweb.dataobject.admin.AdminDO;
import cn.boot.st.managementweb.dataobject.dto.AdminCreateDTO;
import cn.boot.st.managementweb.dataobject.dto.AdminUpdateInfoDTO;
import cn.boot.st.managementweb.dataobject.dto.AdminUpdateStatusDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminConvert {

    AdminConvert INSTANCE = Mappers.getMapper(AdminConvert.class);


    AdminDO convert(AdminCreateDTO bean);


    AdminDO convert(AdminUpdateInfoDTO bean);

    AdminUpdateInfoDTO convert(AdminUpdateStatusDTO bean);


}
