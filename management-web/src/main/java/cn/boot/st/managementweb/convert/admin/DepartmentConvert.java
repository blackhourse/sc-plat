package cn.boot.st.managementweb.convert.admin;

import cn.boot.st.managementweb.dataobject.admin.DepartmentDO;
import cn.boot.st.managementweb.dataobject.dto.DepartmentCreateDTO;
import cn.boot.st.managementweb.dataobject.dto.DepartmentUpdateDTO;
import cn.boot.st.managementweb.dataobject.vo.DepartmentTreeNodeVO;
import cn.boot.st.managementweb.dataobject.vo.DepartmentVO;
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
