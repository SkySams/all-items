package org.event;

import com.google.common.eventbus.Subscribe;

/**
 * @author: zyh
 * @date: 2022/4/18
 */
public class OtherHandler {

    @Subscribe
    public void mq(MqEvent mq) {
        System.err.println(mq.getClass().getCanonicalName()+":"+"OtherHandler work");
    }

}
