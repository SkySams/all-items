package org.example.client.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 链接处理器
 *
 * 通道入站处理程序适配器
 *
 * @author: zyh
 * @date: 2022/3/21
 */
public class ConnectHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("Client:" + ctx.channel().remoteAddress() + " connected");
    }
}
