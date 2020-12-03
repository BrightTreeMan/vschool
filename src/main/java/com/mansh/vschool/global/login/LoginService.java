package com.mansh.vschool.global.login;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.mansh.vschool.custom.ResultData;
import com.mansh.vschool.utils.CacheUtils;
import com.mansh.vschool.utils.CommonUtils;
import com.mansh.vschool.utils.JWTUtils;
import com.mansh.vschool.utils.LogUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginService {
    @Resource
    private RedisTemplate redisTemplate;

    public ResultData<String> login(HttpServletRequest request, HttpServletResponse response, String userName, String password,String code,String curTime) {
        String result = "1";
        if(StrUtil.equalsIgnoreCase(code,(String)redisTemplate.opsForValue().get(curTime))){
            redisTemplate.delete(curTime);
            //添加用户认证信息
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken authToken = new UsernamePasswordToken(userName,password);
            //进行验证，这里可以捕获异常，然后返回对应信息
            try {
                subject.login(authToken);
                JSONObject user = CacheUtils.user();
                String token = JWTUtils.createTokenWithUserName(userName,(user.getJSONObject(userName)).getStr("salt"));
                LogUtils.addloginLog(userName,CommonUtils.getIp(request),"成功","登录成功");
                response.setHeader("x-auth-token", token);
                response.addHeader("Access-Control-Expose-Headers","x-auth-token");
                result = "0";
            } catch (Exception e) {
                LogUtils.addloginLog(userName,CommonUtils.getIp(request),"失败","账户或密码不正确");
            }
        }else{
            //验证码不匹配
            result = "2";
        }
        return new ResultData<>(result).setSuccess(true).setCode("1");
    }
}
