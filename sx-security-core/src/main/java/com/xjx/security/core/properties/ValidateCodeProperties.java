package com.xjx.security.core.properties;

import lombok.Data;

/**
 * @Author XJX
 * @Date 2020/7/13 8:43
 * @Version 1.0
 */
@Data
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();
}
