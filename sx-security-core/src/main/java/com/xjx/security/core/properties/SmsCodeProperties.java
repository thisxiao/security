package com.xjx.security.core.properties;

import lombok.Data;

/**
 * @Author XJX
 * @Date 2020/7/13 8:40
 * @Version 1.0
 */
@Data
public class SmsCodeProperties {

    private int length = 6;
    private int expireIn = 180;

    private String url;
}
