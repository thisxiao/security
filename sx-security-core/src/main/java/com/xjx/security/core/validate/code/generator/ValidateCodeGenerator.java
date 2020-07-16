package com.xjx.security.core.validate.code.generator;

import com.xjx.security.core.validate.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;


/**
 * @Author XJX
 * @Date 2020/7/13 10:21
 * @Version 1.0
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);
}
