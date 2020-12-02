package cn.boot.st.managementweb.controller.admin;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.managementweb.dataobject.dto.AdminCreateDTO;
import cn.boot.st.managementweb.dataobject.dto.AdminUpdateInfoDTO;
import cn.boot.st.managementweb.dataobject.dto.AdminUpdateStatusDTO;
import cn.boot.st.managementweb.service.admin.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
        return success(true);
    }

    @PostMapping("/update-status")
    @ApiOperation(value = "更新管理员状态")
    public CommonResult<Boolean> updateAdminStatus(@Valid AdminUpdateStatusDTO updateStatusDTO) {
        return success(true);
    }


}
