package com.mansh.vschool.global.log.login_log;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mansh.vschool.custom.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
*
* @author mansh
* @since 2020-11-26
*/
@Service
public class MshLoginLogService{
    @Resource
    private MshLoginLogMapper loginLogMapper;

    public ResultData<IPage> loginLogPage(IPage page, MshLoginLogSearch loginLogSearch) {
        return new ResultData<>(loginLogMapper.loginLogPage(page,loginLogSearch)).setSuccess(true);
    }
}