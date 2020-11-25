package com.mht.sc.convert;

import com.mht.sc.dto.SysUserDto;
import com.mht.sc.entity.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysUserConvert {

    public static SysUserConvert MAPPER = Mappers.getMapper(SysUserConvert.class);


     SysUser convert(SysUserDto dto);

}
