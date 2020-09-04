package com.xjx.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;

/**
 * @Author XJX
 * @Date 2020/7/1 9:10
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();
//        http.authorizeRequests().anyRequest().fullyAuthenticated();
//        http.formLogin().loginPage("/login").failureUrl("/login?error").permitAll();
//        http.logout().permitAll();
//        http.csrf().disable();
//        http.authorizeRequests()
//                .anyRequest().permitAll().and().logout().permitAll();//配置不需要登录验证
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
    }
}
