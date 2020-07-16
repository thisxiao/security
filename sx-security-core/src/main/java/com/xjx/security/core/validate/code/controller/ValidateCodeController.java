package com.xjx.security.core.validate.code.controller;

import com.xjx.security.core.properties.SecurityConstants;
import com.xjx.security.core.validate.code.processor.ValidateCodeProcessorHolder;
import com.xjx.security.core.validate.code.processor.ValidateCodeProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author XJX
 * @Date 2020/7/10 16:48
 * @Version 1.0
 */
@RestController
public class ValidateCodeController {

    @Resource
    private ValidateCodeProcessorHolder validateCodeProcessorHolder;

    /**
     * 创建验证码，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
     *
     * @param request 请求
     * @param response 响应
     * @param type 类型
     * @throws Exception the ex
     */
    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response,@PathVariable("type") String type) throws Exception {
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request,response));
    }

}
