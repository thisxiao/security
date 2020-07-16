package com.xjx.security.web.config;

import com.xjx.security.web.filter.TimeFilter;
import com.xjx.security.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author XJX
 * @Date 2020/7/1 10:20
 * @Version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Bean
    public FilterRegistrationBean timeFilter(){
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);
        return registrationBean;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }

//    @Override
//    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
//        configurer.
//    }
}
