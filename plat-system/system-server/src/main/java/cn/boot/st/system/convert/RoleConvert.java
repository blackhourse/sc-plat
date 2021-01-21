package cn.boot.st.system.convert;

import cn.boot.common.framework.vo.PageResult;
import cn.boot.st.system.dataobject.RoleDO;
import cn.boot.st.system.dto.RoleCreateDTO;
import cn.boot.st.system.dto.RoleUpdateDTO;
import cn.boot.st.system.vo.RoleVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Classname RoleConvert
 * @Description
 * @Date 2020/12/5 18:17
 * @Created by maht
 */
@Mapper
public interface RoleConvert {
    RoleConvert INSTANCE = Mappers.getMapper(RoleConvert.class);

    RoleDO convert(RoleCreateDTO bean);

    RoleDO convert(RoleUpdateDTO bean);

    RoleVO convert(RoleDO bean);

    List<RoleVO> convertList(List<RoleDO> beanList);

    @Mapping(source = "records", target = "list")
    PageResult<RoleVO> convertPage(IPage<RoleDO> page);


}
