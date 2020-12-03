package com.mansh.vschool.utils;

import com.mansh.vschool.global.log.login_log.MshLoginLogEntity;
import com.mansh.vschool.global.log.login_log.MshLoginLogMapper;
import com.mansh.vschool.global.log.sys_log.MshSysLogEntity;
import com.mansh.vschool.global.log.sys_log.MshSysLogMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Component
public class LogUtils {
    @Resource
    private MshSysLogMapper sysLogMapper;
    @Resource
    private MshLoginLogMapper loginLogMapper;

    private static LogUtils logUtils;

    @PostConstruct
    public void init(){
        logUtils = this;
    }

    public static void addSysLog(HttpServletRequest request, String operation, String result, String desc){
        MshSysLogEntity log = new MshSysLogEntity();
        log.setDate(System.currentTimeMillis());
        log.setIp(CommonUtils.getIp(request));
        log.setOperation(operation);
        log.setOperationResult(result);
        log.setOperationDesc(desc);
        log.setUserName(JWTUtils.get(CommonUtils.getToken(request),"userName"));
        logUtils.sysLogMapper.insert(log);
    }

    public static void addloginLog(String userName,String ip,String result,String desc){
        MshLoginLogEntity log = new MshLoginLogEntity();
        log.setLoginDate(System.currentTimeMillis());
        log.setLoginDesc(desc);
        log.setLoginIp(ip);
        log.setLoginResult(result);
        log.setUserName(userName);
        logUtils.loginLogMapper.insert(log);
    }
}
