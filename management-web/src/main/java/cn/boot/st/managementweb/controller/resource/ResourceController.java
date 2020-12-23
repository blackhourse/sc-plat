package cn.boot.st.managementweb.controller.resource;

import cn.boot.common.framework.vo.CommonResult;
import cn.boot.st.managementweb.controller.resource.dto.ResourceCreateDTO;
import cn.boot.st.managementweb.controller.resource.dto.ResourceUpdateDTO;
import cn.boot.st.managementweb.controller.resource.vo.ResourceTreeNodeVO;
import cn.boot.st.managementweb.controller.resource.vo.ResourceVO;
import cn.boot.st.managementweb.service.resource.ResourceService;
import cn.boot.st.security.annotations.RequiresPermissions;
import cn.boot.st.security.core.context.AdminSecurityContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static cn.boot.common.framework.vo.CommonResult.success;

/**
 * @Classname ResourceController
 * @Description
 * @Date 2020/12/5
 * @Created by maht
 */
@Api(tags = "资源管理")
@RestController
@RequestMapping(value = "resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @PostMapping("/create")
    @ApiOperation("创建资源")
    @RequiresPermissions("system:resource:create")
    public CommonResult<Integer> createResource(@Valid ResourceCreateDTO createDTO) {
        return success(resourceService.createResource(createDTO, AdminSecurityContextHolder.getAdminId()));
    }

    @PostMapping("/update")
    @ApiOperation("更新资源")
    @RequiresPermissions("system:resource:update")
    public CommonResult<Boolean> updateResource(@Valid ResourceUpdateDTO updateDTO) {
        return success(resourceService.updateResource(updateDTO));
    }


    @PostMapping("/delete")
    @ApiOperation("删除资源")
    @ApiImplicitParam(name = "resourceId", value = "资源编号", required = true)
    @RequiresPermissions("system:resource:delete")
    public CommonResult<Boolean> deleteResource(@RequestParam("resourceId") Integer resourceId) {
        resourceService.deleteResource(resourceId);
        return success(true);
    }


    @GetMapping("/get")
    @ApiOperation("获得资源")
    @RequiresPermissions("system:resource:get")
    public CommonResult<ResourceVO> getResource(@RequestParam("resourceId") Integer resourceId) {
        return success(resourceService.getResource(resourceId));
    }

    @GetMapping("/list")
    @ApiOperation("获得资源列表")
    @ApiImplicitParam(name = "resourceIds", value = "资源编号列表", required = true)
    @RequiresPermissions("system:resource:list")
    public CommonResult<List<ResourceVO>> listResources(@RequestParam("resourceIds") List<Integer> resourceIds) {
        return success(resourceService.listResources(resourceIds));
    }

    @GetMapping("/tree")
    @ApiOperation("获得资源树")
    @RequiresPermissions("system:resource:tree")
    public CommonResult<List<ResourceTreeNodeVO>> treeResource() {
        return success(resourceService.treeResource());
    }


}
