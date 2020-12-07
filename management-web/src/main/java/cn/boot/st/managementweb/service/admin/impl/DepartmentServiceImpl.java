package cn.boot.st.managementweb.service.admin.impl;

import cn.boot.common.framework.exception.util.ServiceException;
import cn.boot.common.framework.exception.util.ServiceExceptionUtil;
import cn.boot.st.managementweb.convert.admin.DepartmentConvert;
import cn.boot.st.managementweb.dataobject.domain.DepartmentDO;
import cn.boot.st.managementweb.dataobject.dto.DepartmentCreateDTO;
import cn.boot.st.managementweb.dataobject.dto.DepartmentUpdateDTO;
import cn.boot.st.managementweb.dataobject.vo.DepartmentTreeNodeVO;
import cn.boot.st.managementweb.dataobject.vo.DepartmentVO;
import cn.boot.common.framework.enums.dept.DepartmentIdEnum;
import cn.boot.st.managementweb.mapper.admin.AdminMapper;
import cn.boot.st.managementweb.mapper.admin.DepartmentMapper;
import cn.boot.st.managementweb.service.admin.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import static cn.boot.common.framework.constant.SystemErrorCodeConstants.*;

/**
 * @Classname DepartmentServiceImpl
 * @Description
 * @Date 2020/12/5 15:51
 * @Created by maht
 */
@Service
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Resource
    private AdminMapper adminMapper;

    @Override
    public Integer createDepartment(DepartmentCreateDTO departmentCreateDTO) {

        // 校验父部门存在
        checkParentDepartment(departmentCreateDTO.getPid(), null);
        // 校验部门（自己）
        checkDepartment(departmentCreateDTO.getPid(), departmentCreateDTO.getName(), null);
        // 插入到数据库
        DepartmentDO departmentDO = DepartmentConvert.INSTANCE.convert(departmentCreateDTO);
        departmentMapper.insert(departmentDO);
        // 返回
        return departmentDO.getId();
    }

    @Override
    public Boolean updateDepartment(DepartmentUpdateDTO departmentUpdateDTO) {
        //校验更新的部门的是否存在
        if (departmentMapper.selectById(departmentUpdateDTO.getId()) == null) {
            throw ServiceExceptionUtil.exception(DEPARTMENT_NOT_FOUND);
        }
        // 校验父部门
        checkParentDepartment(departmentUpdateDTO.getPid(), departmentUpdateDTO.getId());
        checkDepartment(departmentUpdateDTO.getPid(), departmentUpdateDTO.getName(), departmentUpdateDTO.getId());
        DepartmentDO departmentDO = DepartmentConvert.INSTANCE.convert(departmentUpdateDTO);
        departmentMapper.updateById(departmentDO);
        return Boolean.TRUE;
    }

    @Override
    public void deleteDepartment(Integer departmentId) {
        // 校验删除的部门是否存在
        if (departmentMapper.selectById(departmentId) == null) {
            throw new ServiceException(DEPARTMENT_NOT_FOUND);
        }
        //
        if (adminMapper.selectCountByDepartmentId(departmentId) > 0) {
            throw ServiceExceptionUtil.exception(DEPARTMENT_NOT_FOUND);
        }
        // 标记删除
        departmentMapper.deleteById(departmentId);
    }

    @Override
    public DepartmentVO getDepartment(Integer departmentId) {
        DepartmentDO departmentDO = departmentMapper.selectById(departmentId);
        return DepartmentConvert.INSTANCE.convert(departmentDO);
    }

    @Override
    public List<DepartmentVO> listDepartments(List<Integer> departmentIds) {
        List<DepartmentDO> departmentDOs = departmentMapper.selectBatchIds(departmentIds);
        return DepartmentConvert.INSTANCE.convertList(departmentDOs);
    }

    @Override
    public List<DepartmentTreeNodeVO> treeDepartment() {
        List<DepartmentDO> departmentDOs = departmentMapper.selectList(null);
        List<DepartmentVO> departmentVOs = DepartmentConvert.INSTANCE.convertList(departmentDOs);
        // 先排序
        departmentVOs.sort(Comparator.comparing(DepartmentVO::getSort));

        // build 树
        LinkedHashMap<Integer, DepartmentTreeNodeVO> treeNodeMap = new LinkedHashMap<>();
        departmentVOs.stream().forEach(departmentVO -> treeNodeMap.put(departmentVO.getId(), DepartmentConvert.INSTANCE.convertTreeNode(departmentVO)));
        // 父子关系
        treeNodeMap.values().stream().filter(node -> !node.getPid().equals(DepartmentIdEnum.ROOT.getId())).forEach(childNode -> {

            // 获得父节点
            DepartmentTreeNodeVO parentNode = treeNodeMap.get(childNode.getPid());
            if (parentNode == null) {
                log.error("[buildDepartmentTree][department({}) 找不到父部门({})]", childNode.getId(), childNode.getPid());
                return;
            }
            // 将自己添加到父节点中
            if (parentNode.getChildren() == null) {
                parentNode.setChildren(new ArrayList<>());
            }
            parentNode.getChildren().add(childNode);
        });
        // 获得到所有的根节点
        return treeNodeMap.values().stream().filter(node -> node.getPid().equals(DepartmentIdEnum.ROOT.getId())).collect(Collectors.toList());
    }


    /**
     * 校验父部门是否合法
     * <p>
     * 1. 不能设置自己为父部门
     * 2. 父部门不存在
     *
     * @param pid     父部门编号
     * @param childId 当前部门编号
     */
    private void checkParentDepartment(Integer pid, Integer childId) {
        if (pid == null || DepartmentIdEnum.ROOT.getId().equals(pid)) {
            return;
        }
        // 不能设置自己为父部门
        if (pid.equals(childId)) {
            throw new ServiceException(DEPARTMENT_PARENT_ERROR);
        }
        DepartmentDO resource = departmentMapper.selectById(pid);
        // 父部门不存在
        if (resource == null) {
            throw ServiceExceptionUtil.exception(DEPARTMENT_PARENT_NOT_EXITS);
        }
    }

    /**
     * 校验部门是否合法
     * <p>
     * 1. 校验相同父部门编号下，是否存在相同的部门名
     *
     * @param name 部门名字
     * @param pid  父部门编号
     * @param id   部门编号
     */
    private void checkDepartment(Integer pid, String name, Integer id) {
        DepartmentDO resource = departmentMapper.selectByPidAndName(pid, name);
        if (resource == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的部门
        if (id == null) {
            throw ServiceExceptionUtil.exception(DEPARTMENT_NAME_DUPLICATE);
        }
        if (!resource.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(DEPARTMENT_NAME_DUPLICATE);
        }
    }
}
