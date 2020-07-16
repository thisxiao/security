package com.xjx.security.core.validate.code.sms.impl;

import com.xjx.security.core.validate.code.sms.SmsCodeSender;

/**
 * @Author XJX
 * @Date 2020/7/13 14:27
 * @Version 1.0
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向"+mobile+"发送短信验证码：" + code);
    }
}
