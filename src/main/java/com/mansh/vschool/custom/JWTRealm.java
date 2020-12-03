package com.mansh.vschool.custom;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mansh.vschool.global.user.user.MshUserEntity;
import com.mansh.vschool.global.user.user.MshUserMapper;
import com.mansh.vschool.utils.JWTUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

public class JWTRealm extends AuthorizingRealm {
    @Resource
    private MshUserMapper userMapper;

    public JWTRealm(){
        setCredentialsMatcher(new JWTMatcher());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected SimpleAuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)  {
        String token = (String) authenticationToken.getPrincipal();
        String userName = JWTUtils.get(token,"userName");
        QueryWrapper query = new QueryWrapper();
        query.eq("user_name",userName);
        MshUserEntity user = userMapper.selectOne(query);
        if (user == null) {
            throw new UnknownAccountException(); // 账号不存在
        }
        if (StrUtil.equals(user.getState(),"1")) {
            throw new LockedAccountException();  // 账号被锁定
        }
        if(JWTUtils.isExpires(token)){
            throw new ExpiredCredentialsException();//token过期
        }
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getSalt()))
                .withIssuer("mansh")
                .build();
        try{
            jwtVerifier.verify(token);
        } catch (Exception e){
            throw new CredentialsException();//token信息伪造
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user,
                token,
                getName());
        return authenticationInfo;
    }
}
