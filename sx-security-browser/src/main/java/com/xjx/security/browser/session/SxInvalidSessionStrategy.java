package com.xjx.security.browser.session;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author XJX
 * @Date 2020/7/17 15:04
 * @Version 1.0
 *
 * session超时的处理策略
 */
public class SxInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

    public SxInvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        onSessionInvalid(request,response);
    }
}
