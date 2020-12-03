package com.mansh.vschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mansh.vschool"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().title("测试系统接口详细信息")
                        .version("1.0")
                        .contact(new Contact("man","https://mail.163.com/","mansh0427@163.com"))
                        .license("The Apache License")
                        .licenseUrl("http://www.baidu.com")
                        .build()
                );
    }

    @Bean
    public UiConfiguration uiConfiguration(){
        return UiConfigurationBuilder.builder().defaultModelsExpandDepth(-1).build();
    }

}
