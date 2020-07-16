package com.xjx.security.service.impl;

import com.xjx.security.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @Author XJX
 * @Date 2020/6/30 16:22
 * @Version 1.0
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String greeting(String name) {
        System.out.println("greeting");
        return "hello" + name;
    }
}
