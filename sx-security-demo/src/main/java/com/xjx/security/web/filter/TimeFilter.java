package com.xjx.security.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @Author XJX
 * @Date 2020/7/1 10:09
 * @Version 1.0
 */
//@Component
@Slf4j
public class TimeFilter implements Filter {

    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("time filter init");
        config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("time filter start");
        long start = new Date().getTime();
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("time filter 耗时:{}" , (new Date().getTime() - start));
        log.info("time filter finish");
        String sqlInjectStrList = this.config.getInitParameter("sqlInjectStrList");
        log.info("sqlInjectSerList:{}",sqlInjectStrList);
    }

    @Override
    public void destroy() {
        log.info("time filter destroy");
        config = null;
    }
}
