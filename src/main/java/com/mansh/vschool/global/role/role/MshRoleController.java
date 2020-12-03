package com.mansh.vschool.global.role.role;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mansh.vschool.custom.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;

/**
*
* @author mansh
* @since 2020-11-26
*/
@RestController
@RequestMapping("/role")
@Api(tags = "角色管理接口")
public class MshRoleController {
    @Resource
    private MshRoleService roleService;

    @ApiOperation("分页查询角色列表")
    @PostMapping("/rolePage")
    public ResultData<IPage> rolePage(MshRoleSearch roleSearch){
        Page page = new Page(roleSearch.getCurrent(),roleSearch.getSize());
        return roleService.rolePage(page,roleSearch);
    }

    @ApiOperation("新增角色")
    @PostMapping("/roleAdd")
    public ResultData<Integer> roleAdd(HttpServletRequest request, MshRoleEntity role){
        return roleService.roleAdd(request,role);
    }

    @ApiOperation("修改角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId",value = "角色ID",required = true,example = "1",dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "roleName",value = "角色名称",required = true,example = "名称",dataTypeClass = String.class),
            @ApiImplicitParam(name = "roleDesc",value = "角色描述",required = true,example = "描述",dataTypeClass = String.class),
            @ApiImplicitParam(name = "roleMenuId",value = "角色-菜单ID",required = true,example = "[1]",dataTypeClass = Array.class)
    })
    @PutMapping("/roleEdit/{roleId}/{roleName}/{roleDesc}/{roleMenuId}")
    public ResultData<Integer> roleEdit(HttpServletRequest request, @PathVariable("roleId") int roleId, @PathVariable("roleName") String roleName, @PathVariable("roleDesc") String roleDesc, @PathVariable("roleMenuId") int[] roleMenuId){
        return roleService.roleEdit(request,roleId,roleName,roleDesc,roleMenuId);
    }

    @ApiOperation("删除角色")
    @ApiImplicitParam(name = "roleId",value = "角色ID",required = true,example = "1",dataTypeClass = Integer.class)
    @DeleteMapping("/roleDel/{roleId}")
    public ResultData<Integer> roleDel(HttpServletRequest request, @PathVariable("roleId") int roleId){
        return roleService.remove(request,roleId);
    }

}
