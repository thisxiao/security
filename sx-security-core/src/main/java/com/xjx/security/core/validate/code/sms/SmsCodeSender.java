package com.xjx.security.core.validate.code.sms;

/**
 * @Author XJX
 * @Date 2020/7/13 14:26
 * @Version 1.0
 */
public interface SmsCodeSender {
    void send(String mobile,String code);
}
