package com.xjx.security.browser.logout;

import com.alibaba.fastjson.JSON;
import com.xjx.security.core.properties.SecurityProperties;
import com.xjx.security.core.support.SimpleResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author XJX
 * @Date 2020/7/20 10:43
 * @Version 1.0
 */
@Slf4j
public class SxLogoutSuccessHandler implements LogoutSuccessHandler {

    private String signOutUrl;


    public SxLogoutSuccessHandler(SecurityProperties securityProperties){
        this.signOutUrl = securityProperties.getBrowser().getSignOutUrl();
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("退出成功！");

        if(StringUtils.isBlank(signOutUrl)){
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSON.toJSONString(new SimpleResponse("退出成功！")));
        }else{
            response.sendRedirect(signOutUrl);
        }
    }
}
