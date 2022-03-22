package org.example.handler;

import io.netty.buffer.ByteBuf;
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
public class ObjectServerHandler extends SimpleChannelInboundHandler<Peple> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Peple msg) throws Exception {
        System.out.println("------------");
        System.out.println(msg);
        System.out.println(msg.getUser());
        System.out.println(msg.getAge());
    }


    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }



}
