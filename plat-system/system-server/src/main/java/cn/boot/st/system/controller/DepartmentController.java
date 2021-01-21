package cn.boot.st.system.controller;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.system.dto.DepartmentCreateDTO;
import cn.boot.st.system.dto.DepartmentUpdateDTO;
import cn.boot.st.system.service.DepartmentService;
import cn.boot.st.system.vo.DepartmentTreeNodeVO;
import cn.boot.st.system.vo.DepartmentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static cn.boot.common.framework.vo.CommonResult.success;

/**
 * @Classname DeptController
 * @Description 部门控制类
 * @Date 2020/12/5 15:48
 * @Created by maht
 */
@RestController
@RequestMapping("/department")
@Api(tags = "部门")
@Validated
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/create")
    @ApiOperation("创建部门")
    public CommonResult<Integer> createDepartment(@Valid DepartmentCreateDTO createDTO) {
        return success(departmentService.createDepartment(createDTO));
    }

    @PostMapping("/update")
    @ApiOperation("更新部门")
    public CommonResult<Boolean> updateDepartment(@Valid DepartmentUpdateDTO updateDTO) {
        return success(departmentService.updateDepartment(updateDTO));
    }

    @PostMapping("/delete")
    @ApiOperation("删除部门")
    @ApiImplicitParam(name = "departmentId", value = "部门编号", required = true)
    public CommonResult<Boolean> deleteDepartment(@RequestParam("departmentId") Integer departmentId) {
        departmentService.deleteDepartment(departmentId);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得部门")
    @ApiImplicitParam(name = "departmentId", value = "部门编号", required = true)
    public CommonResult<DepartmentVO> getDepartment(@RequestParam("departmentId") Integer departmentId) {
        return success(departmentService.getDepartment(departmentId));
    }

    @GetMapping("/list")
    @ApiOperation("获得部门列表")
    @ApiImplicitParam(name = "departmentIds", value = "部门编号列表", required = true)
    public CommonResult<List<DepartmentVO>> listDepartments(@RequestParam("departmentIds") List<Integer> departmentIds) {
        return success(departmentService.listDepartments(departmentIds));
    }


    @GetMapping("/tree")
    @ApiOperation("获得部门树")
    public CommonResult<List<DepartmentTreeNodeVO>> treeDepartment() {
        return success(departmentService.treeDepartment());
    }


}
