package com.xjx.security.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author XJX
 * @Date 2020/7/1 9:53
 * @Version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserNotExistException extends RuntimeException {

    private static final long serialVersionUID = 1355956272402230267L;

    private String id;

    public UserNotExistException(String id) {
        super("user not exist");
        this.id = id;
    }
}
