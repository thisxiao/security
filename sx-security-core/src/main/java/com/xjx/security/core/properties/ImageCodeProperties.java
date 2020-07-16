package com.xjx.security.core.properties;

import lombok.Data;

/**
 * @Author XJX
 * @Date 2020/7/13 8:40
 * @Version 1.0
 */
@Data
public class ImageCodeProperties extends SmsCodeProperties{

    public ImageCodeProperties(){
        setLength(4);
    }

    private int width = 67;
    private int height = 23;

}
