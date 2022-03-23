package org.example.chapter6;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author: zyh
 * @date: 2022/3/23
 */
@ChannelHandler.Sharable
public class SimpleDiscardHandler
        extends SimpleChannelInboundHandler<Object> {
    @Override
    public void channelRead0(ChannelHandlerContext ctx,
                             Object msg) {
        // No need to do anything special
    }
}