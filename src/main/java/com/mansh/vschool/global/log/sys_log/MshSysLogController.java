package com.mansh.vschool.global.log.sys_log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mansh.vschool.custom.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
*
* @author mansh
* @since 2020-11-26
*/
@RestController
@RequestMapping("/sysLog")
@Api(tags = "系统日志管理接口")
public class MshSysLogController {
    @Resource
    private MshSysLogService sysLogService;

    @ApiOperation("分页查询登录日志列表")
    @PostMapping("/sysLogPage")
    public ResultData<IPage> sysLogPage(MshSysLogSearch sysLogSearch){
        Page page = new Page(sysLogSearch.getCurrent(),sysLogSearch.getSize());
        return sysLogService.sysLogPage(page,sysLogSearch);
    }

    @ApiOperation("获取操作类型列表-选择")
    @GetMapping("/operationList")
    public ResultData<List<MshSysLogEntity>> operationList(){
        return sysLogService.getOperationList();
    }

}
