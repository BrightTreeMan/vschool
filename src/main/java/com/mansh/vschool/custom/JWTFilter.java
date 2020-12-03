package com.mansh.vschool.custom;

import cn.hutool.core.util.StrUtil;
import com.mansh.vschool.global.user.user.MshUserEntity;
import com.mansh.vschool.utils.CommonUtils;
import com.mansh.vschool.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Slf4j
public class JWTFilter extends AuthenticatingFilter {
    @Resource
    private JWTUtils jwtUtils;

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse){
        String jwtToken = CommonUtils.getToken(WebUtils.toHttp(servletRequest));
        if(StrUtil.isNotBlank(jwtToken)){
            return new JWTToken(jwtToken);
        }
        return new JWTToken("jwtToken");
    }

    /**
     *
     * isPermissive(mappedValue) shiroConfig可配置
     * "/logout", "noSessionCreation,authcToken[permissive]" 跳过验证
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        boolean isAllow = false;
        try {
            //这个方法首先会createToken()，然后调用shiro的Subject.login()
            isAllow = executeLogin(request, response);
        } catch (Exception e) {
            log.error(CommonUtils.exceptionToString(e));
        }

        return isAllow || isPermissive(mappedValue);
    }

    /**
     * 拦截options请求
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){//对于OPTION请求做拦截，不做token校验
            return false;
        }

        return super.preHandle(request, response);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
        HttpServletResponse httpResponse = WebUtils.toHttp(servletResponse);
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json;charset=UTF-8");
        fillCorsHeader(WebUtils.toHttp(servletRequest), httpResponse);
        Writer out = httpResponse.getWriter();
        out.write(new ResultData().setCode("-1").setSuccess(false).setMessage("登录状态已失效，请重新登陆").toString());
        out.close();
        return false;
    }

    /**
     * 成功后是否刷新token
     * 快到期的话，返回一个新的
     * @param token
     * @param subject
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response){
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        String newToken = null;
        if(token instanceof JWTToken){
            JWTToken jwtToken = (JWTToken)token;
            MshUserEntity user = (MshUserEntity) subject.getPrincipal();
            if(jwtUtils.shouldFreshToken(jwtToken.getToken())){
                newToken = jwtUtils.createTokenWithUserName(user.getUserName(),user.getSalt());
            }
        }

        if(StrUtil.isNotBlank(newToken)){
            httpResponse.setHeader("x-auth-token", newToken);
            httpResponse.addHeader("Access-Control-Expose-Headers","x-auth-token");
        }

        return true;
    }

    private void fillCorsHeader(HttpServletRequest request, HttpServletResponse response){
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,HEAD");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
    }

}
