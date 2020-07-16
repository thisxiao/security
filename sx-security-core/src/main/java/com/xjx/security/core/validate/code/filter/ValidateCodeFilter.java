package com.xjx.security.core.validate.code.filter;

import com.xjx.security.core.properties.SecurityConstants;
import com.xjx.security.core.properties.SecurityProperties;
import com.xjx.security.core.validate.code.config.ValidateCodeType;
import com.xjx.security.core.validate.code.exception.ValidateCodeException;
import com.xjx.security.core.validate.code.processor.ValidateCodeProcessorHolder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @Author XJX
 * @Date 2020/7/10 17:13
 * @Version 1.0
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * 验证码校验失败处理器
     */
    @Resource
    private AuthenticationFailureHandler authenticationFailureHandler;
    /**
     * 存放所有需要校验验证码的url
     */
    private Map<String, ValidateCodeType> urlMap = new HashMap<>();
    /**
     * 系统配置信息
     */
    @Resource
    private SecurityProperties securityProperties;
    /**
     * 系统中的校验码处理器
     */
    @Resource
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;
    /**
     * 验证请求url与配置的url是否匹配工具
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 初始化需要拦截的url配置信息
     * @throws ServletException the ex
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM,ValidateCodeType.IMAGE);
        addUrlToMap(securityProperties.getCode().getImage().getUrl(),ValidateCodeType.IMAGE);

        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, ValidateCodeType.SMS);
        addUrlToMap(securityProperties.getCode().getSms().getUrl(), ValidateCodeType.SMS);
    }

    /**
     * 讲系统中配置的需要校验验证码的URL根据校验的类型放入map
     *
     * @param urlString 路径
     * @param type 类型
     */
    protected void addUrlToMap(String urlString, ValidateCodeType type) {
        if (StringUtils.isNotBlank(urlString)) {
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            for (String url : urls) {
                urlMap.put(url, type);
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(
     * javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ValidateCodeType type = getValidateCodeType(request);
        if (type != null) {
            logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + type);
            try {
                validateCodeProcessorHolder.findValidateCodeProcessor(type)
                        .validate(new ServletWebRequest(request, response));
                logger.info("验证码校验通过");
            } catch (ValidateCodeException exception) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, exception);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request 请求
     * @return 校验码类型
     */
    private ValidateCodeType getValidateCodeType(HttpServletRequest request) {
        ValidateCodeType result = null;
        if (!StringUtils.equalsIgnoreCase(request.getMethod(), "get")) {
            Set<String> urls = urlMap.keySet();
            for (String url : urls) {
                if (pathMatcher.match(url, request.getRequestURI())) {
                    result = urlMap.get(url);
                }
            }
        }
        return result;
    }
}
