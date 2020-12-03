package com.mansh.vschool.custom;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mansh.vschool.global.user.user.MshUserEntity;
import com.mansh.vschool.global.user.user.MshUserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;

public class UserRealm extends AuthorizingRealm {
    @Resource
    private MshUserMapper userMapper;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected SimpleAuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)  {
        String userName = (String) authenticationToken.getPrincipal();
        QueryWrapper query = new QueryWrapper();
        query.eq("user_name",userName);
        MshUserEntity user = userMapper.selectOne(query);
        if (user == null) {
            throw new UnknownAccountException(); // 账号不存在
        }
        if (StrUtil.equals(user.getState(),"1")) {
            throw new LockedAccountException();  // 账号被锁定
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getSalt()),
                getName());
        return authenticationInfo;
    }
}
