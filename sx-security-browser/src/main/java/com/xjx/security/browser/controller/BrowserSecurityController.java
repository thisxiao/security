package com.xjx.security.browser.controller;

import com.xjx.security.browser.pojo.SocialUserInfo;
import com.xjx.security.browser.support.SimpleResponse;
import com.xjx.security.core.properties.SecurityConstants;
import com.xjx.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author XJX
 * @Date 2020/7/9 17:08
 * @Version 1.0
 */
@Slf4j
@RestController
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Resource
    private SecurityProperties securityProperties;

    @Resource
    private ProviderSignInUtils providerSignInUtils;

    /**
     * 当需要身份认证时，跳转到这里
     * @param request 请求
     * @param response 响应
     * @return 认证结果
     */
    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取引发跳转的请求
        SavedRequest savedRequest = requestCache.getRequest(request,response);

        //如果有这个引发跳转的请求的话,且该请求以.html结尾,将重定向到我们的默认登陆页/sx-signIn.html
        if(savedRequest != null){
            //获取请求的url
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("请求跳转的请求是：{}",targetUrl);

            //如果请求url以.html结尾跳转到我们自己写的登录页----在前后端分离的项目里一般不会这样做
            if(StringUtils.endsWithIgnoreCase(targetUrl,".html")){
                redirectStrategy.sendRedirect(request,response,securityProperties.getBrowser().getLoginPage());
            }
        }
        //如果有引发跳转的请求且不以html结尾
        //或者如果没有引发跳转的请求----即直接访问authentication/require
        //返回一个未认证的状态码并引导用户进行登陆
        return new SimpleResponse("访问的服务需要身份验证，请指导用到登录页面!");
    }

    /**
     * 获取第三方用户信息
     * @param request 请求
     * @return 第三方用户信息
     */
    @GetMapping("/social/user")
    public SocialUserInfo getSocialUserInfo(HttpServletRequest request){
        SocialUserInfo userInfo = new SocialUserInfo();
        //从session里取出封装了第三方信息QQ用户信息）的Connection对象
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
        userInfo.setProviderId(connection.getKey().getProviderId());
        userInfo.setProviderUserId(connection.getKey().getProviderUserId());
        userInfo.setNickName(connection.getDisplayName());
        userInfo.setHeadImg(connection.getImageUrl());
        return userInfo;
    }

    /**
     * Session失效
     * @return response
     */
    @GetMapping("/session/invalid")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse sessionInvalid(){
        String message = "session失效";
        return new SimpleResponse(message);
    }
}
