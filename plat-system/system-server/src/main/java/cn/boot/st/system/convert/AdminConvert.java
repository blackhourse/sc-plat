package cn.boot.st.system.convert;

import cn.boot.common.framework.dataobject.vo.AdminUserTokenInfo;
import cn.boot.st.system.dataobject.AdminDO;
import cn.boot.st.system.dto.AdminCreateDTO;
import cn.boot.st.system.dto.AdminUpdateInfoDTO;
import cn.boot.st.system.dto.AdminUpdateStatusDTO;
import cn.boot.st.system.vo.PassportAdminVO;
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
