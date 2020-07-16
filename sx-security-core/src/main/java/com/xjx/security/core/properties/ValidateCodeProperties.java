package com.xjx.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author XJX
 * @Date 2020/7/13 8:43
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "sx.security.code")
public class ValidateCodeProperties {
    /**
     * 封装图片验证码相关属性
     */
    private ImageCodeProperties image = new ImageCodeProperties();
    /**
     * 封装短信验证码相关属性
     */
    private SmsCodeProperties sms = new SmsCodeProperties();
}
