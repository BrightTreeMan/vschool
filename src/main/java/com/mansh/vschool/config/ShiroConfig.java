package com.mansh.vschool.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.Setting;
import com.mansh.vschool.custom.JWTFilter;
import com.mansh.vschool.custom.JWTRealm;
import com.mansh.vschool.custom.ParamConst;
import com.mansh.vschool.custom.UserRealm;
import org.apache.shiro.authc.Authenticator;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.mgt.SessionStorageEvaluator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSessionStorageEvaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * 配置url过滤器
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(){
        Setting vschool = new Setting("static/vschool.setting");
        String model = vschool.getStr("model");
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        if(StrUtil.equals(model,"dev")){
            //测试模式
            chainDefinition.addPathDefinition("/**", "noSessionCreation,anon");
        }else{
            chainDefinition.addPathDefinition("/login","noSessionCreation,anon");
            //验证码
            chainDefinition.addPathDefinition("/authCodeImage/**","noSessionCreation,anon");
            //swagger shiro config start
            chainDefinition.addPathDefinition("/doc.html", "noSessionCreation,anon");
            chainDefinition.addPathDefinition("/swagger-resources/**", "noSessionCreation,anon");
            chainDefinition.addPathDefinition("/webjars/**", "noSessionCreation,anon");
            chainDefinition.addPathDefinition("/v2/**", "noSessionCreation,anon");
            //swagger shiro config end
            chainDefinition.addPathDefinition("/**", "noSessionCreation,JWTToken");
        }
        return chainDefinition;
    }


    /**
     * 配置自定义Realm
     * @return
     */
    @Bean
    public Realm userRealm(){
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(credentialsMatcher());
        return userRealm;
    }

    @Bean
    public Realm jwtRealm() {
        JWTRealm jwtRealm = new JWTRealm();
        return jwtRealm;
    }

    /**
     * 设置用于匹配密码的CredentialsMatcher
     * @return
     */
    @Bean
    public HashedCredentialsMatcher credentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);  // 散列算法，这里使用更安全的sha256算法
        credentialsMatcher.setHashIterations(ParamConst.HASH_ITERATIONS);
        return credentialsMatcher;
    }

    @Bean
    public Authenticator authenticator() {
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
        authenticator.setRealms(Arrays.asList(jwtRealm(), userRealm()));
        authenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return authenticator;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
        ShiroFilterFactoryBean filterFactory = new ShiroFilterFactoryBean();
        filterFactory.setSecurityManager(securityManager);
        Map<String, Filter> filterMap = filterFactory.getFilters();
        filterMap.put("JWTToken",new JWTFilter());
        filterFactory.setFilters(filterMap);
        filterFactory.setFilterChainDefinitionMap(shiroFilterChainDefinition().getFilterChainMap());

        return filterFactory;
    }

    @Bean
    protected SessionStorageEvaluator sessionStorageEvaluator(){
        DefaultWebSessionStorageEvaluator sessionStorageEvaluator = new DefaultWebSessionStorageEvaluator();
        sessionStorageEvaluator.setSessionStorageEnabled(false);
        return sessionStorageEvaluator;
    }
}
