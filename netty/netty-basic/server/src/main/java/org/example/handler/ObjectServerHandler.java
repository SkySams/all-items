package org.example.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.example.entity.Peple;

/**
 * @author: zyh
 * @date: 2022/3/22
 */
@ChannelHandler.Sharable
public class ObjectServerHandler extends SimpleChannelInboundHandler{


    /**
     *
     * @param ctx the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *            belongs to
     * @param msg the message to handle
     * @throws Exception is thrown if an error occurred
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Peple peple = (Peple) msg;
        System.out.println(peple);
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
        cause.printStackTrace();
        ctx.close();
    }



}
