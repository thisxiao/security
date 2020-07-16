package com.xjx.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xjx.security.browser.support.SimpleResponse;
import com.xjx.security.core.properties.LoginType;
import com.xjx.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author XJX
 * @Date 2020/7/10 11:37
 * @Version 1.0
 */
@Slf4j
@Component("szAuthenticationFailureHandler")
public class SzAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info("登录失败！");
        if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())){
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(e.getMessage())));
        }else{
            super.onAuthenticationFailure(httpServletRequest,httpServletResponse,e);
        }
    }
}
