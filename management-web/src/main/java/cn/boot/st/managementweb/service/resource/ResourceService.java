package cn.boot.st.managementweb.service.resource;

import cn.boot.st.managementweb.dataobject.dto.ResourceCreateDTO;
import cn.boot.st.managementweb.dataobject.dto.ResourceUpdateDTO;
import cn.boot.st.managementweb.dataobject.vo.ResourceTreeNodeVO;
import cn.boot.st.managementweb.dataobject.vo.ResourceVO;

import java.util.List;

/**
 * @Classname ResourcrService
 * @Description
 * @Date 2020/12/5
 * @Created by maht
 */
public interface ResourceService {

    /**
     * 资源添加
     *
     * @param createDTO
     * @param createAdminId
     * @return
     */
    Integer createResource(ResourceCreateDTO createDTO, Integer createAdminId);

    /**
     * 资源更新
     *
     * @param resourceUpdateDTO
     * @return
     */
    Boolean updateResource(ResourceUpdateDTO resourceUpdateDTO);

    /**
     * 删除-资源
     *
     * @param resourceId
     */
    void deleteResource(Integer resourceId);

    /**
     * 获取资源
     *
     * @param resourceId
     * @return
     */
    ResourceVO getResource(Integer resourceId);

    /**
     * 资源列表
     *
     * @param resourceIds
     * @return
     */
    List<ResourceVO> listResources(List<Integer> resourceIds);

    /**
     * 资源树
     * @return
     */
    List<ResourceTreeNodeVO> treeResource();

}
