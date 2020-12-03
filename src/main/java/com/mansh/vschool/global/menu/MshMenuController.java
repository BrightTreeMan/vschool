package com.mansh.vschool.global.menu;

import com.mansh.vschool.custom.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
*
* @author mansh
* @since 2020-11-25
*/
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单管理接口")
public class MshMenuController {
    @Resource
    private MshMenuService menuService;

    @ApiOperation("获取菜单-展示")
    @GetMapping("/menuList/{userName}")
    public ResultData menuList(@PathVariable("userName")String userName){
        return menuService.menuList(userName);
    }

    @ApiOperation("获取菜单-选择")
    @GetMapping("/menuListForChoose")
    public ResultData menuListForChoose(){
        return menuService.menuListForChoose();
    }
}
