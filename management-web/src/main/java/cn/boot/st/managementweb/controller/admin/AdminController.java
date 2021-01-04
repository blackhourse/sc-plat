package cn.boot.st.managementweb.controller.admin;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.managementweb.controller.admin.dto.AdminCreateDTO;
import cn.boot.st.managementweb.controller.admin.dto.AdminUpdateInfoDTO;
import cn.boot.st.managementweb.controller.admin.dto.AdminUpdateStatusDTO;
import cn.boot.st.managementweb.service.admin.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collection;

import static cn.boot.common.framework.vo.CommonResult.success;

@Api(tags = "管理员api")
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "创建管理员")
    @PostMapping("/create")
    public CommonResult<Integer> createAdmin(@RequestBody AdminCreateDTO createDTO, HttpServletRequest request) {
        return success(adminService.createAdmin(createDTO));
    }

    @PostMapping("/update")
    @ApiOperation(value = "更新管理员")
    public CommonResult<Boolean> updateAdmin(AdminUpdateInfoDTO updateInfoDTO) {
        adminService.updateAdmin(updateInfoDTO);
        return success(true);
    }

    @PostMapping("/update-status")
    @ApiOperation(value = "更新管理员状态")
    public CommonResult<Boolean> updateAdminStatus(@Valid AdminUpdateStatusDTO updateStatusDTO) {
        adminService.updateAdminStatus(updateStatusDTO);
        return success(true);
    }

    /**
     * 判断角色是否有超级管理员
     *
     * @param roleIds 角色编号列表
     * @return 是否有超级管理员
     */
    @GetMapping("/is-admin")
    @ApiOperation("判断角色是否有超级管理员")
    @ApiImplicitParam(name = "roleIds", value = "角色编号列表", required = true)
    public CommonResult<Boolean> hasSuperAdmin(@RequestParam("roleIds") Collection<Integer> roleIds) {
        return success(adminService.hasSuperAdmin(roleIds));
    }


}
