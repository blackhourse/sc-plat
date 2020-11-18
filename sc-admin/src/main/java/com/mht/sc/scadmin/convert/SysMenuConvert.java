package com.mht.sc.scadmin.convert;

import com.mht.sc.scadmin.dto.SysMenuAddOrUpdateDto;
import com.mht.sc.scadmin.dto.SysUserDto;
import com.mht.sc.scadmin.entity.SysMenu;
import com.mht.sc.scadmin.entity.SysUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysMenuConvert {

    public static SysMenuConvert MAPPER = Mappers.getMapper(SysMenuConvert.class);


    SysMenu convert(SysMenuAddOrUpdateDto dto);

}
