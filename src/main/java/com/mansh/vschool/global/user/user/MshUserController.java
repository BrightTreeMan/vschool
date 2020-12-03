package com.mansh.vschool.global.user.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mansh.vschool.custom.ResultData;
import com.mansh.vschool.global.role.role.MshRoleEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.List;

/**
*
* @author mansh
* @since 2020-10-12
*/
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理接口")
public class MshUserController {
    @Resource
    private MshUserService userService;

    @ApiOperation("新增用户")
    @PostMapping("/userAdd")
    public ResultData<Integer> userAdd(HttpServletRequest request,MshUserEntity user){
        return userService.userAdd(request,user);
    }

    @ApiOperation("检测账户唯一性")
    @ApiImplicitParam(name = "userName",value = "账户",required = true,example = "root",dataTypeClass = String.class)
    @GetMapping("/userCheck/{userName}")
    public ResultData<String> userCheck(@PathVariable("userName") String userName){
        return userService.userCheck(userName);
    }

    @ApiOperation("重置密码")
    @ApiImplicitParam(name = "userId",value = "用户ID",required = true,example = "1",dataTypeClass = Integer.class)
    @PutMapping("/resetPwd/{userId}")
    public ResultData<Integer> resetPwd(HttpServletRequest request,@PathVariable("userId") int userId){
        return userService.resetPwd(request,userId);
    }

    @ApiOperation("锁定/解锁用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID",required = true,example = "1",dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "state",value = "状态",required = true,example = "1",dataTypeClass = String.class)
    })
    @PutMapping("/userLock/{userId}/{state}")
    public ResultData<Integer> userLock(HttpServletRequest request,@PathVariable("userId") int userId,@PathVariable("state") String state){
        return userService.userLock(request,userId,state);
    }

    @ApiOperation("获取角色列表-选择")
    @GetMapping("/userRoleList")
    public ResultData<List<MshRoleEntity>> userRoleList(){
        return userService.userRoleList();
    }

    @ApiOperation("修改用户的角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID",required = true,example = "1",dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "userRoleId",value = "用户关联的角色ID组",required = true,example = "1",dataTypeClass = Array.class)
    })
    @PutMapping("/userRole/{userId}/{userRoleId}")
    public ResultData<Integer> userRole(HttpServletRequest request, @PathVariable("userId") int userId,@PathVariable("userRoleId")int[] userRoleId){
        return userService.userRole(request,userId,userRoleId);
    }

    @ApiOperation("修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID",required = true,example = "1",dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "deptId",value = "部门ID",required = true,example = "1",dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "nickName",value = "用户名称",required = true,example = "root",dataTypeClass = String.class),
            @ApiImplicitParam(name = "phone",value = "手机号",required = true,example = "13111111111",dataTypeClass = String.class)
    })
    @PutMapping("/userEdit/{userId}/{deptId}/{nickName}/{phone}")
    public ResultData<Integer> userEdit(HttpServletRequest request, @PathVariable("userId") int userId, @PathVariable("deptId") int deptId, @PathVariable("nickName") String nickName, @PathVariable("phone") String phone){
        return userService.userEdit(request,userId,deptId,nickName,phone);
    }

    @ApiOperation("分页查询用户列表")
    @PostMapping("/userPage")
    public ResultData<IPage> userPage(MshUserSearch userSearch){
        Page page = new Page(userSearch.getCurrent(),userSearch.getSize());
        return userService.userPage(page,userSearch);
    }

    @ApiOperation("删除用户")
    @ApiImplicitParam(name = "userId",value = "用户ID",required = true,example = "1",dataTypeClass = Integer.class)
    @DeleteMapping("/userDel/{userId}")
    public ResultData<Integer> userDel(HttpServletRequest request, @PathVariable("userId") int userId){
        return userService.remove(request,userId);
    }

    @ApiOperation("获取用户列表-选择")
    @GetMapping("/userList")
    public ResultData<List<MshUserEntity>> userList(){
        return userService.userList();
    }

    @ApiOperation("修改用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName",value = "用户账号",required = true,example = "root",dataTypeClass = String.class),
            @ApiImplicitParam(name = "password",value = "新密码",required = true,example = "123456",dataTypeClass = String.class),
            @ApiImplicitParam(name = "oldPassword",value = "原密码",required = true,example = "111111",dataTypeClass = String.class),
    })
    @PutMapping("/changePwd/{userName}/{password}/{oldPassword}")
    public ResultData<Integer> changePwd(HttpServletRequest request, @PathVariable("userName") String userName, @PathVariable("password") String password, @PathVariable("oldPassword") String oldPassword){
        return userService.changePwd(request,userName,password,oldPassword);
    }
}
