package cn.boot.st.system.convert;


import cn.boot.st.system.dataobject.DepartmentDO;
import cn.boot.st.system.dto.DepartmentCreateDTO;
import cn.boot.st.system.dto.DepartmentUpdateDTO;
import cn.boot.st.system.vo.DepartmentTreeNodeVO;
import cn.boot.st.system.vo.DepartmentVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Classname DepartmentConvert
 * @Description
 * @Date 2020/12/5 16:04
 * @Created by maht
 */
@Mapper
public interface DepartmentConvert {
    DepartmentConvert INSTANCE = Mappers.getMapper(DepartmentConvert.class);

    DepartmentDO convert(DepartmentCreateDTO bean);

    DepartmentDO convert(DepartmentUpdateDTO bean);

    DepartmentVO convert(DepartmentDO bean);

    List<DepartmentVO> convertList(List<DepartmentDO> list);

    DepartmentTreeNodeVO convertTreeNode(DepartmentVO bean);

}
