package com.xjx.security.core.validate.code.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author XJX
 * @Date 2020/7/10 17:18
 * @Version 1.0
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 4501236763118457093L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
