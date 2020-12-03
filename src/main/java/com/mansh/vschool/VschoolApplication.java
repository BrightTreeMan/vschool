package com.mansh.vschool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.mansh.vschool.global")
@EnableSwagger2
public class VschoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(VschoolApplication.class, args);
    }

}
