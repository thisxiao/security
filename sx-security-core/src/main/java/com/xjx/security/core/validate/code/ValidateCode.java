package com.xjx.security.core.validate.code;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author XJX
 * @Date 2020/7/13 11:40
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateCode {
    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
