package com.xjx.security.validator;

import com.xjx.security.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author XJX
 * @Date 2020/6/30 16:18
 * @Version 1.0
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {

    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint constraintAnnotation) {
        System.out.println("my validator init");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        helloService.greeting("tom");
        System.out.println(o);
        return false;
    }
}
