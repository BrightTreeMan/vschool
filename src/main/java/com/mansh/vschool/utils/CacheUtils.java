package com.mansh.vschool.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mansh.vschool.custom.ParamConst;
import com.mansh.vschool.exception.MSHException;
import com.mansh.vschool.global.sys_param.MshSysParamEntity;
import com.mansh.vschool.global.sys_param.MshSysParamMapper;
import com.mansh.vschool.global.user.user.MshUserMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CacheUtils {
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private MshSysParamMapper sysParamMapper;

    @Resource
    private MshUserMapper userMapper;

    private static CacheUtils cacheUtils;

    @PostConstruct
    public void init(){
        cacheUtils = this;
    }

    public static void initCache(){
        //启动项目的时候查询数据 存入redis
        //sys_param表
        QueryWrapper sysParamQuery = new QueryWrapper();
        sysParamQuery.eq("param_item","system");
        Map sysParamMap = (Map) cacheUtils.sysParamMapper.selectList(sysParamQuery).stream().collect(Collectors.toMap(MshSysParamEntity::getParamName,MshSysParamEntity::getParamVal));
        cacheUtils.redisTemplate.opsForValue().set("sysParam",JSONUtil.parseObj(sysParamMap).toString(), Duration.ofMinutes(ParamConst.CACHE_INTERVAL_OF_MINUTES));

        //user表 userName - userEntity
        Map userMap = cacheUtils.userMapper.selectList(null).stream().collect(Collectors.toMap(item -> item.getUserName(),item -> item));
        cacheUtils.redisTemplate.opsForValue().set("user",JSONUtil.parseObj(userMap).toString(), Duration.ofMinutes(ParamConst.CACHE_INTERVAL_OF_MINUTES));

    }

    public static JSONObject sysParam(){
        return JSONUtil.parseObj(get("sysParam"));
    }

    public static JSONObject user(){
        return JSONUtil.parseObj(get("user"));
    }

    public static void reload(String type){
        switch(type){
            case "user":
                Map userMap = cacheUtils.userMapper.selectList(null).stream().collect(Collectors.toMap(item -> item.getUserName(),item -> item));
                cacheUtils.redisTemplate.opsForValue().set("user",JSONUtil.parseObj(userMap).toString(), Duration.ofMinutes(ParamConst.CACHE_INTERVAL_OF_MINUTES));
                break;
            default:
                break;
        }

    }

    public static Object get(String key){
        Object reStr = cacheUtils.redisTemplate.opsForValue().get(key);
        if(reStr == null){
            initCache();
            reStr = cacheUtils.redisTemplate.opsForValue().get(key);
            if(reStr == null){
                throw new MSHException("key:"+key+" 不在缓存列表中");
            }
        }
        return reStr;
    }
}
