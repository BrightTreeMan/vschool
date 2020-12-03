package com.mansh.vschool.global.log.login_log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mansh.vschool.custom.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
*
* @author mansh
* @since 2020-11-26
*/
@RestController
@RequestMapping("/loginLog")
@Api(tags = "登录日志管理接口")
public class MshLoginLogController {
    @Resource
    private MshLoginLogService loginLogService;

    @ApiOperation("分页查询登录日志列表")
    @PostMapping("/loginLogPage")
    public ResultData<IPage> loginLogPage(MshLoginLogSearch loginLogSearch){
        Page page = new Page(loginLogSearch.getCurrent(),loginLogSearch.getSize());
        return loginLogService.loginLogPage(page,loginLogSearch);
    }
}
