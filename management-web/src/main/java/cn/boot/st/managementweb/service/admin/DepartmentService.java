package cn.boot.st.managementweb.service.admin;

import cn.boot.st.managementweb.dataobject.dto.DepartmentCreateDTO;
import cn.boot.st.managementweb.dataobject.dto.DepartmentUpdateDTO;
import cn.boot.st.managementweb.dataobject.vo.DepartmentTreeNodeVO;
import cn.boot.st.managementweb.dataobject.vo.DepartmentVO;

import java.util.List;

/**
 * @Classname DepartmentService
 * @Description
 * @Date 2020/12/5 15:51
 * @Created by maht
 */
public interface DepartmentService {


    /**
     * 部门创建
     *
     * @param departmentCreateDTO
     * @return
     */
    Integer createDepartment(DepartmentCreateDTO departmentCreateDTO);

    /**
     * 部门更新
     *
     * @param departmentUpdateDTO
     * @return
     */
    Boolean updateDepartment(DepartmentUpdateDTO departmentUpdateDTO);

    /**
     * 删除部门
     *
     * @param departmentId
     */
    void deleteDepartment(Integer departmentId);

    /**
     * 获取部门信息
     *
     * @param departmentId
     * @return
     */
    DepartmentVO getDepartment(Integer departmentId);

    /**
     * 部门列表
     *
     * @param departmentIds
     * @return
     */
    List<DepartmentVO> listDepartments(List<Integer> departmentIds);

    /**
     * 部门树
     * @return
     */
    List<DepartmentTreeNodeVO> treeDepartment();

}
