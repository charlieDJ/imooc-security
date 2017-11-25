package com.imooc.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private MockQueue mockQueue;
    @Autowired
    private DeferredResultHolder resultHolder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //因为容器启动的时候会扫描这个类，不新开线程会造成阻塞
        new Thread(()->{
            while (true){
                if(!StringUtils.isEmpty(mockQueue.getCompleteOrder())){//获取订单号是否存在
                    String orderNumber = mockQueue.getCompleteOrder();
                    logger.info("返回订单处理结果");
                    //取出订单号对应的返回结果，设置新的返回结果
                    resultHolder.getMap().get(orderNumber).setResult("place order success");
                    mockQueue.setCompleteOrder(null);
                }else{
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
