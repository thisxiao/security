package com.xjx.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author XJX
 * @Date 2020/7/10 10:07
 * @Version 1.0
 * "大盒子"类-----------------用于统一管理项目中所有由yml或properties文件传入的变量值
 */
@Data
@ConfigurationProperties(prefix = "sx.security")
public class SecurityProperties {
    /***封装浏览器相关的属性*/
    private BrowserProperties browser = new BrowserProperties();
    /***验证码相关的属性---可能包含图形验证码，短信验证码等，所以对其进行了又一次封装*/
    private ValidateCodeProperties code = new ValidateCodeProperties();
    /***springSocial相关的配置*/
    private SocialProperties social = new SocialProperties();
}
