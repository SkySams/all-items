package org.example.channel;

import io.netty.channel.Channel;
import io.netty.channel.DefaultChannelPipeline;

/**
 * @author: zyh
 * @date: 2022/3/22
 */
public class DummyChannelPipeline extends DefaultChannelPipeline {
    protected DummyChannelPipeline(Channel channel) {
        super(channel);
    }
}
