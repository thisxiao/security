package com.xjx.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xjx.security.core.properties.LoginType;
import com.xjx.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author XJX
 * @Date 2020/7/10 11:08
 * @Version 1.0
 */
@Slf4j
@Component("szAuthenticationSuccessHandler")
public class SzAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功！");

        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else{
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }

    }
}
