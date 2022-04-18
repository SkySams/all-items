package org.event;

import com.google.common.eventbus.EventBus;

/**
 * @author: zyh
 * @date: 2022/4/18
 */
public class EventTest {

    public static void main(String[] args) {
        //初始化消息总线
        EventBus eventBus = new EventBus();
        // 注册订阅者
        eventBus.register(new EventHandler());
        eventBus.register(new OtherHandler());
        //MqEvent推送给了两个订阅者
        MqEvent mqEvent = new MqEvent();
        StatusEvent statusEvent = new StatusEvent();
        //发布消息
        eventBus.post(mqEvent);
        eventBus.post(statusEvent);
    }

}
