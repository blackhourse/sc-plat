package com.mht.sc.scadmin.convert;

import com.mht.sc.scadmin.dto.SysUserDto;
import com.mht.sc.scadmin.entity.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserConvert {

    public static SysUserConvert MAPPER = Mappers.getMapper(SysUserConvert.class);


     SysUser convert(SysUserDto dto);

}
