package com.xjx.security.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * @Author XJX
 * @Date 2020/7/1 15:26
 * @Version 1.0
 */
@Slf4j
@RestController
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    @RequestMapping("/order")
    public DeferredResult<String> order() throws InterruptedException {
        log.info("主线程开始");

        String random = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(random);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(random,result);
//        Callable<String> result = ()-> {
//            log.info("副线程开始");
//            Thread.sleep(1000);
//            log.info("副线程返回");
//            return "SUCCESS";
//        };

        log.info("主线程返回");
        return result;
    }
}
