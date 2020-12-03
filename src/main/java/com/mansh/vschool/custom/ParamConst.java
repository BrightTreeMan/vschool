package com.mansh.vschool.custom;

public class ParamConst {
    public static final int HASH_ITERATIONS = 2;//散列次数
    public static final int JWT_TOKEN_LAST_TIME_OF_MINUTES = 30;//jwtToken持续时间
    public static final long JWT_TOKEN_REFLUSH_INTERVAL = 5 * 60 * 1000;//是否刷新JWT的间隔 毫秒级
    public static final long CACHE_INTERVAL_OF_MINUTES = 30;//缓存有效时间 分钟
    public static final long AUTH_CODE_LAST_TIME_OF_MINUTES = 10;//验证码有效时间 分钟
}
