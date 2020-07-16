package com.xjx.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author XJX
 * @Date 2020/7/13 8:40
 * @Version 1.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "sx.security.code.sms")
public class SmsCodeProperties {

    /**短信验证码的长度*/
    private int length = 6;
    /**验证码的有效期*/
    private int expireIn = 180;
    /**需要使用验证码校验的所有URL，需以逗号分隔开*/
    private String url;
}
