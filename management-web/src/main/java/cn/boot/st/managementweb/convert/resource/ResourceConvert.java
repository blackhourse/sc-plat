package cn.boot.st.managementweb.convert.resource;

import cn.boot.st.managementweb.dataobject.domain.ResourceDO;
import cn.boot.st.managementweb.dataobject.domain.RoleResourceDO;
import cn.boot.st.managementweb.controller.resource.dto.ResourceCreateDTO;
import cn.boot.st.managementweb.controller.resource.dto.ResourceUpdateDTO;
import cn.boot.st.managementweb.controller.resource.vo.ResourceTreeNodeVO;
import cn.boot.st.managementweb.controller.resource.vo.ResourceVO;
import cn.boot.st.managementweb.controller.permission.vo.RoleResourceVo;
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

    List<RoleResourceVo> convertRoleResourceVoList(List<RoleResourceDO> bean);

}
