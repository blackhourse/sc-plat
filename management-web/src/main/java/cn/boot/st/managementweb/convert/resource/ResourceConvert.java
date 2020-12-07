package cn.boot.st.managementweb.convert.resource;

import cn.boot.st.managementweb.dataobject.domain.ResourceDO;
import cn.boot.st.managementweb.dataobject.dto.ResourceCreateDTO;
import cn.boot.st.managementweb.dataobject.dto.ResourceUpdateDTO;
import cn.boot.st.managementweb.dataobject.vo.ResourceTreeNodeVO;
import cn.boot.st.managementweb.dataobject.vo.ResourceVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @Classname RoleConvert
 * @Description
 * @Date 2020/12/5 18:17
 * @Created by maht
 */
@Mapper
public interface ResourceConvert {
    ResourceConvert INSTANCE = Mappers.getMapper(ResourceConvert.class);


    ResourceDO convert(ResourceCreateDTO bean);

    ResourceDO convert(ResourceUpdateDTO bean);

    ResourceVO convert(ResourceDO bean);

    List<ResourceVO> convertList(List<ResourceDO> bean);

    ResourceTreeNodeVO convertTreeNode(ResourceVO bean);

}