package com.xjx.security.conn;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * @Author XJX
 * @Date 2020/7/15 17:41
 * @Version 1.0
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息默认创建用户并返回用户唯一标识
        return connection.getDisplayName();
    }
}
