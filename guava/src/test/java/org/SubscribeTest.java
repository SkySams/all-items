package org;

import com.google.common.eventbus.EventBus;
import org.junit.jupiter.api.Test;

/**
 * @author: zyh
 * @date: 2022/4/18
 */
public class SubscribeTest {

    @Test
    public void should_create_event_bus_instance() throws Exception {
        EventBus eventBus = new EventBus();
        //string构造参数，用于标识EventBus
        EventBus eventBus1 = new EventBus("My Event Bus");
    }

}
