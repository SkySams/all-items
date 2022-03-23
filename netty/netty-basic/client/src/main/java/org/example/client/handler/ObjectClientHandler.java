package org.example.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.example.entity.Peple;

/**
 * @author: zyh
 * @date: 2022/3/22
 */
public class ObjectClientHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Peple peple = (Peple) msg;
        System.out.println(peple.getAge());
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Peple peple;
        for (int i = 1; i< 10;i++){
            peple = new Peple();
            peple.setUser("sky"+1);
            peple.setAge(i);
            ctx.writeAndFlush(peple);
        }
    }



    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("客户端发生异常:" + cause);
        cause.printStackTrace();
        ctx.close();
    }



}
