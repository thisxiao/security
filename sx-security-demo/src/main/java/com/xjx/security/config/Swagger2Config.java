package com.xjx.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author XJX
 * @Date 2020/7/1 17:00
 * @Version 1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket createRestAPi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()//初始化并返回一个API选择构造器
                .apis(RequestHandlerSelectors.basePackage("com.xjx.security.web.controller"))//添加路径选择条件扫描接口-> basePackage(final String basePackage)：返回一个断言(Predicate)，该断言包含所有匹配basePackage下所有类的请求路径的请求处理器;	any()：返回包含所有满足条件的请求处理器的断言，该断言总为true;	none()：返回不满足条件的请求处理器的断言,该断言总为false
                .paths(PathSelectors.any())//设置路径筛选 -> any():满足条件的路径，该断言总为true;	none():不满足条件的路径,该断言总为false;	regex(final String pathRegex):符合正则的路径
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试 APIS")
                .description("了解更多请联系：")
                .termsOfServiceUrl("http://www.xxxx.com")
                .version("1.0")
                .build();
    }
}
