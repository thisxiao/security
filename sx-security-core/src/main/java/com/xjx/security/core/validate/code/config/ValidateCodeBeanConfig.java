package com.xjx.security.core.validate.code.config;

import com.xjx.security.core.validate.code.generator.ValidateCodeGenerator;
import com.xjx.security.core.validate.code.image.ImageValidateCodeGenerator;
import com.xjx.security.core.validate.code.sms.SmsCodeSender;
import com.xjx.security.core.validate.code.sms.impl.DefaultSmsCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author XJX
 * @Date 2020/7/13 10:29
 * @Version 1.0
 */
@Configuration
public class ValidateCodeBeanConfig {

    /**
     * 启动时先扫描容器是否有imageCodeGenerator的Bean，没有则注入
     * @return the ValidateCodeGenerator
     */
    @Bean
    @ConditionalOnMissingBean(ImageValidateCodeGenerator.class)
    public ValidateCodeGenerator imageValidateCodeGenerator(){
        return new ImageValidateCodeGenerator();
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }

}
