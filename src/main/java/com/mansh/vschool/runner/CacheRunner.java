package com.mansh.vschool.runner;

import com.mansh.vschool.utils.CacheUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CacheRunner implements CommandLineRunner {
    @Override
    public void run(String... args){
        CacheUtils.initCache();
    }
}
