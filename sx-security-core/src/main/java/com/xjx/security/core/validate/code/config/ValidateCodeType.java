package com.xjx.security.core.validate.code.config;

import com.xjx.security.core.properties.SecurityConstants;

/**
 * @Author XJX
 * @Date 2020/7/13 15:16
 * @Version 1.0
 */
public enum ValidateCodeType {
    /**
     * 短信验证码
     */
    SMS{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
        }
    },
    /**
     * 图片验证码
     */
    IMAGE{
        @Override
        public String getParamNameOnValidate() {
            return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
        }
    };

    /**
     * 校验时从请求中获取参数的名字
     * @return 参数名
     */
    public abstract String getParamNameOnValidate();
}
