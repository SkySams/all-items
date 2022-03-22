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
@ChannelHandler.Sharable
public class ObjectClientHandler extends SimpleChannelInboundHandler<Peple> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Peple peple) throws Exception {
        System.out.println("------------");
        System.out.println(peple);
        System.out.println(peple.getUser());
        System.out.println(peple.getAge());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Peple peple = new Peple();
        peple.setAge(12);
        peple.setUser("123");
        ctx.writeAndFlush(Unpooled.copiedBuffer(peple.toString(), CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

}
