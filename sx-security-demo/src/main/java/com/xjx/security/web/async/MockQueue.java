package com.xjx.security.web.async;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author XJX
 * @Date 2020/7/1 15:43
 * @Version 1.0
 */
@Slf4j
@Component
public class MockQueue {

    private String placeOrder;

    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder)  {
        new Thread(()->{
            log.info("接到下单请求:{}",placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            log.info("下单请求完毕：{}",placeOrder);
        }).start();
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
