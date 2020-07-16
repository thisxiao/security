package com.xjx.security.code;

import com.xjx.security.core.validate.code.generator.ValidateCodeGenerator;
import com.xjx.security.core.validate.code.image.ImageCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author XJX
 * @Date 2020/7/13 10:38
 * @Version 1.0
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生成代码");
        return null;
    }
}
