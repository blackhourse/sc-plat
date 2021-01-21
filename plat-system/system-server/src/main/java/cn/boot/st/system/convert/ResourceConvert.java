package cn.boot.st.system.convert;


import cn.boot.st.system.dataobject.ResourceDO;
import cn.boot.st.system.dataobject.RoleResourceDO;
import cn.boot.st.system.dto.ResourceCreateDTO;
import cn.boot.st.system.dto.ResourceUpdateDTO;
import cn.boot.st.system.vo.ResourceTreeNodeVO;
import cn.boot.st.system.vo.ResourceVO;
import cn.boot.st.system.vo.RoleResourceVo;
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
