package org.event;

import com.google.common.eventbus.Subscribe;

/**
 * @author: zyh
 * @date: 2022/4/18
 */
public class EventHandler {

    @Subscribe
    public void mq(MqEvent mq) {
        System.err.println(mq.getClass().getCanonicalName()+" work");
    }

    @Subscribe
    public void status(StatusEvent statusEvent) {
        System.err.println(statusEvent.getClass().getCanonicalName() +" work");
    }

}
