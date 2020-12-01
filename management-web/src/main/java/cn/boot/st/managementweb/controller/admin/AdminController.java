package cn.boot.st.managementweb.controller.admin;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.managementweb.mysql.dataobject.admin.AdminDO;
import cn.boot.st.managementweb.service.admin.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.boot.common.framework.vo.CommonResult.success;

@Api(tags = "管理员api")
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @ApiOperation(value = "测试")
    @GetMapping("/{id}")
    public CommonResult<AdminDO> getById(@PathVariable Integer id) {
        return success(adminService.getById(id));
    }

}
