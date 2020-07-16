package com.xjx.security.core.validate.code.processor;

import org.springframework.web.context.request.ServletWebRequest;


/**
 * 校验码处理器，封装不同校验码的处理逻辑
 *
 * @Author XJX
 * @Date 2020/7/13 15:01
 * @Version 1.0
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建校验码
     * @param request 请求
     * @throws Exception the ex
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     * @param request 请求
     */
    void validate(ServletWebRequest request);
}
