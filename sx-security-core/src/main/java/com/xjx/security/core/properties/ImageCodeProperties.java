package com.xjx.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author XJX
 * @Date 2020/7/13 8:40
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Component
@ConfigurationProperties(prefix = "sx.security.code.image")
public class ImageCodeProperties extends SmsCodeProperties{

    /**保证初始化ImageCodeProperties时length的默认值为4*/
    public ImageCodeProperties(){
        setLength(4);
    }

    /**验证码图片的宽度*/
    private int width = 67;

    /**验证码图片的高度*/
    private int height = 23;

}
