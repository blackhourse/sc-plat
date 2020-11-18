package com.mht.sc.scadmin.convert;

import com.mht.sc.scadmin.dto.SysRoleAddOrUpdateDto;
import com.mht.sc.scadmin.entity.SysRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysRoleConvert {

    public static SysRoleConvert MAPPER = Mappers.getMapper(SysRoleConvert.class);


    SysRole convert(SysRoleAddOrUpdateDto dto);

}
