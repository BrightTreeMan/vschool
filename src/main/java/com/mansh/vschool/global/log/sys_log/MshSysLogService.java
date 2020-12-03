package com.mansh.vschool.global.log.sys_log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mansh.vschool.custom.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
*
* @author mansh
* @since 2020-11-26
*/
@Service
public class MshSysLogService{
    @Resource
    private MshSysLogMapper sysLogMapper;

    public ResultData<IPage> sysLogPage(IPage page,MshSysLogSearch sysLogSearch) {
        return new ResultData<>(sysLogMapper.sysLogPage(page,sysLogSearch)).setSuccess(true);
    }

    public ResultData<List<MshSysLogEntity>> getOperationList() {
        return new ResultData<>(sysLogMapper.operationList()).setSuccess(true);
    }
}