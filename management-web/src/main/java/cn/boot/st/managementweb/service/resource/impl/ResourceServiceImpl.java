package cn.boot.st.managementweb.service.resource.impl;

import cn.boot.common.framework.constant.SystemErrorCodeConstants;
import cn.boot.common.framework.enums.role.ResourceIdEnum;
import cn.boot.common.framework.enums.role.ResourceTypeEnum;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.st.managementweb.convert.resource.ResourceConvert;
import cn.boot.st.managementweb.dataobject.domain.ResourceDO;
import cn.boot.st.managementweb.controller.resource.dto.ResourceCreateDTO;
import cn.boot.st.managementweb.controller.resource.dto.ResourceUpdateDTO;
import cn.boot.st.managementweb.controller.resource.vo.ResourceTreeNodeVO;
import cn.boot.st.managementweb.controller.resource.vo.ResourceVO;
import cn.boot.st.managementweb.mapper.role.ResourceMapper;
import cn.boot.st.managementweb.mapper.role.RoleResourceMapper;
import cn.boot.st.managementweb.service.resource.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static cn.boot.common.framework.constant.SystemErrorCodeConstants.*;

/**
 * @Classname ResourceServiceImpl
 * @Description
 * @Date 2020/12/5
 * @Created by maht
 */
@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Override
    public Integer createResource(ResourceCreateDTO createDTO, Integer createAdminId) {
        // 校验父资源存在
        checkParentResource(createDTO.getPid(), null);
        // 校验资源（自己）
        checkResource(createDTO.getPid(), createDTO.getName(), null);
        // 插入数据库
        ResourceDO resourceDO = ResourceConvert.INSTANCE.convert(createDTO);
        initResourceProperty(resourceDO);
        resourceMapper.insert(resourceDO);
        // 返回
        return resourceDO.getId();
    }

    @Override
    public Boolean updateResource(ResourceUpdateDTO resourceUpdateDTO) {
        // 校验更新的资源是否存在
        if (resourceMapper.selectById(resourceUpdateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(RESOURCE_NOT_EXISTS);
        }
        // 校验父资源存在
        checkParentResource(resourceUpdateDTO.getPid(), resourceUpdateDTO.getId());
        // 校验资源（自己）
        checkResource(resourceUpdateDTO.getPid(), resourceUpdateDTO.getName(), resourceUpdateDTO.getId());
        // 更新到数据库
        ResourceDO updateObject = ResourceConvert.INSTANCE.convert(resourceUpdateDTO);
        initResourceProperty(updateObject);
        resourceMapper.updateById(updateObject);
        return Boolean.TRUE;
    }

    @Override
    public void deleteResource(Integer resourceId) {
        // 校验更新的资源是否存在
        if (resourceMapper.selectById(resourceId) == null) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeConstants.RESOURCE_NOT_EXISTS);
        }
        // 校验是否还有子资源
        if (resourceMapper.selectCountByPid(resourceId) > 0) {
            throw ServiceExceptionUtil.exception(SystemErrorCodeConstants.RESOURCE_EXISTS_CHILDREN);
        }
        // 标记删除
        resourceMapper.deleteById(resourceId);
        // 删除授予给角色的权限
        roleResourceMapper.deleteByResourceId(resourceId);
    }

    @Override
    public ResourceVO getResource(Integer resourceId) {
        ResourceDO resourceDO = resourceMapper.selectById(resourceId);
        return ResourceConvert.INSTANCE.convert(resourceDO);
    }

    @Override
    public List<ResourceVO> listResources(List<Integer> resourceIds) {
        List<ResourceDO> resourceDOs = resourceMapper.selectBatchIds(resourceIds);
        return ResourceConvert.INSTANCE.convertList(resourceDOs);
    }

    @Override
    public List<ResourceTreeNodeVO> treeResource() {

        List<ResourceDO> resourceDOs = resourceMapper.selectList(null);
        List<ResourceVO> resourceVOs = ResourceConvert.INSTANCE.convertList(resourceDOs);
        return buildResourceTree(resourceVOs);
    }




    /**
     * 校验父资源是否合法
     * <p>
     * 1. 不能设置自己为父资源
     * 2. 父资源不存在
     * 3. 父资源必须是 {@link ResourceTypeEnum#MENU} 菜单类型
     *
     * @param pid     父资源编号
     * @param childId 当前资源编号
     */
    private void checkParentResource(Integer pid, Integer childId) {
        if (pid == null || ResourceIdEnum.ROOT.getId().equals(pid)) {
            return;
        }
        // 不能设置自己为父资源
        if (pid.equals(childId)) {
            throw ServiceExceptionUtil.exception(RESOURCE_PARENT_ERROR);
        }
        ResourceDO resource = resourceMapper.selectById(pid);
        // 父资源不存在
        if (resource == null) {
            throw ServiceExceptionUtil.exception(RESOURCE_PARENT_NOT_EXISTS);
        }
        // 父资源必须是菜单类型
        if (!ResourceTypeEnum.MENU.getType().equals(resource.getType())) {
            throw ServiceExceptionUtil.exception(RESOURCE_PARENT_NOT_MENU);
        }
    }

    /**
     * 校验资源是否合法
     * <p>
     * 1. 校验相同父资源编号下，是否存在相同的资源名
     *
     * @param name 资源名字
     * @param pid  父资源编号
     * @param id   资源编号
     */
    private void checkResource(Integer pid, String name, Integer id) {
        ResourceDO resource = resourceMapper.selectByPidAndName(pid, name);
        if (resource == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的资源
        if (id == null) {
            throw ServiceExceptionUtil.exception(RESOURCE_NAME_DUPLICATE);
        }
        if (!resource.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(RESOURCE_NAME_DUPLICATE);
        }
    }

    /**
     * 初始化资源的通用属性。
     * <p>
     * 例如说，只有菜单类型的资源，才设置 icon
     *
     * @param resource 资源
     */
    private void initResourceProperty(ResourceDO resource) {
        // 资源为按钮类型时，无需 route、icon、view 属性，进行置空
        if (ResourceTypeEnum.BUTTON.getType().equals(resource.getType())) {
            resource.setRoute(null);
            resource.setIcon(null);
            resource.setView(null);
        }
    }

    /**
     * 构建资源树
     *
     * @param resourceVOs 资源列表
     * @return 资源树
     */
    public static List<ResourceTreeNodeVO> buildResourceTree(List<ResourceVO> resourceVOs) {
        // 排序，保证菜单的有序性
        resourceVOs.sort(Comparator.comparing(ResourceVO::getSort));
        // 构建菜单树
        Map<Integer, ResourceTreeNodeVO> treeNodeMap = new LinkedHashMap<>();
        resourceVOs.forEach(resourceVO -> treeNodeMap.put(resourceVO.getId(), ResourceConvert.INSTANCE.convertTreeNode(resourceVO)));
        // 处理父子关系
        treeNodeMap.values().stream().filter(node -> !node.getPid().equals(ResourceIdEnum.ROOT.getId())).forEach((childNode) -> {
            // 获得父节点
            ResourceTreeNodeVO parentNode = treeNodeMap.get(childNode.getPid());
            if (parentNode == null) {
                log.error("[buildResourceTree][resource({}) 找不到父资源({})]", childNode.getId(), childNode.getPid());
                return;
            }
            // 将自己添加到父节点中
            if (parentNode.getChildren() == null) {
                parentNode.setChildren(new ArrayList<>());
            }
            parentNode.getChildren().add(childNode);
        });
        // 获得到所有的根节点
        return treeNodeMap.values().stream().filter(node -> node.getPid().equals(ResourceIdEnum.ROOT.getId())).collect(Collectors.toList());
    }


}
