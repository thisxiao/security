package com.xjx.security.core.validate.code.processor;

import com.xjx.security.core.validate.code.config.ValidateCodeType;
import com.xjx.security.core.validate.code.exception.ValidateCodeException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @Author XJX
 * @Date 2020/7/13 15:53
 * @Version 1.0
 */
@Component
public class ValidateCodeProcessorHolder {

    /**
     * 收集系统中所有的{@link ValidateCodeProcessor} 接口的实现
     */
    @Resource
    private Map<String,ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type){
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor validateCodeProcessor = validateCodeProcessors.get(name);
        if(validateCodeProcessor == null){
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return validateCodeProcessor;
    }
}
