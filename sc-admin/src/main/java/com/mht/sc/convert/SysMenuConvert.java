package com.mht.sc.convert;

import com.mht.sc.entity.SysMenu;
import com.mht.sc.dto.SysMenuAddOrUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SysMenuConvert {

    public static SysMenuConvert MAPPER = Mappers.getMapper(SysMenuConvert.class);


    SysMenu convert(SysMenuAddOrUpdateDto dto);

}
