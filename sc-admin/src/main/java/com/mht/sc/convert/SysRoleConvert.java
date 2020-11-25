package com.mht.sc.convert;

import com.mht.sc.dto.SysRoleAddOrUpdateDto;
import com.mht.sc.entity.SysRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysRoleConvert {

    public static SysRoleConvert MAPPER = Mappers.getMapper(SysRoleConvert.class);


    SysRole convert(SysRoleAddOrUpdateDto dto);

}
