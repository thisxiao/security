package com.xjx.security.core.validate.code.sms;

import com.xjx.security.core.properties.SecurityProperties;
import com.xjx.security.core.validate.code.ValidateCode;
import com.xjx.security.core.validate.code.generator.ValidateCodeGenerator;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;

/**
 * @Author XJX
 * @Date 2020/7/13 14:38
 * @Version 1.0
 */
@Component
public class SmsValidateCodeGenerator implements ValidateCodeGenerator {

    @Resource
    private SecurityProperties securityProperties;

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code,securityProperties.getCode().getSms().getExpireIn());
    }
}
