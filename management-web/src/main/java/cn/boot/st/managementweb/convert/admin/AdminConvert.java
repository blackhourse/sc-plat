package cn.boot.st.managementweb.convert.admin;

import cn.boot.common.framework.dataobject.vo.AdminUserTokenInfo;
import cn.boot.st.managementweb.controller.admin.dto.AdminCreateDTO;
import cn.boot.st.managementweb.controller.admin.dto.AdminUpdateInfoDTO;
import cn.boot.st.managementweb.controller.admin.dto.AdminUpdateStatusDTO;
import cn.boot.st.managementweb.controller.login.vo.PassportAdminVO;
import cn.boot.st.managementweb.dataobject.domain.AdminDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminConvert {

    AdminConvert INSTANCE = Mappers.getMapper(AdminConvert.class);


    AdminDO convert(AdminCreateDTO bean);


    AdminDO convert(AdminUpdateInfoDTO bean);

    AdminUpdateInfoDTO convert(AdminUpdateStatusDTO bean);

    PassportAdminVO convert(AdminDO bean);

    AdminUserTokenInfo convertTokenInfo(AdminDO adminDO);

}
