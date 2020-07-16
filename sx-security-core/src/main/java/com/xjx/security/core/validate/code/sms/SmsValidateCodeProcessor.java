package com.xjx.security.core.validate.code.sms;

import com.xjx.security.core.properties.SecurityConstants;
import com.xjx.security.core.validate.code.ValidateCode;
import com.xjx.security.core.validate.code.processor.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;

/**
 * 短信验证码处理器
 * @Author XJX
 * @Date 2020/7/13 15:50
 * @Version 1.0
 */
@Component
public class SmsValidateCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     */
    @Resource
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile, validateCode.getCode());
    }
}
