package com.xjx.security.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @Author XJX
 * @Date 2020/7/1 10:30
 * @Version 1.0
 */
@Slf4j
@Component
public class TimeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");
        String remoteAddr = request.getRemoteAddr();
        log.info("remoteAddr:{}",remoteAddr);
        String remoteHost = request.getRemoteHost();
        log.info("remoteHost:{}",remoteHost);
        int remotePort = request.getRemotePort();
        log.info("remotePort:{}",remotePort);
        int serverPort = request.getServerPort();
        log.info("serverPort:{}",serverPort);
        int localPort = request.getLocalPort();
        log.info("localPort:{}",localPort);
        if(handler instanceof HandlerMethod){
            log.info(((HandlerMethod)handler).getBean().getClass().getName());
            log.info(((HandlerMethod)handler).getMethod().getName());
        }
        request.setAttribute("startTime",new Date().getTime());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
        Long startTime = (Long) request.getAttribute("startTime");
        log.info("time interceptor 耗时：{}",(new Date().getTime() - startTime));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
        Long startTime = (Long) request.getAttribute("startTime");
        log.info("time interceptor 耗时：{}",(new Date().getTime() - startTime));
        log.error("ex is:" + ex);
    }
}
