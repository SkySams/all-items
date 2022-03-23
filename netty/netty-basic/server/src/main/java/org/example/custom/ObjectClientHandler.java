package org.example.custom;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.example.entity.Peple;

/**
 * @author: zyh
 * @date: 2022/3/23
 */
public class ObjectClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("active");
        super.channelActive(ctx);
        //发送消息给服务端
        Peple peple = new Peple();
        peple.setAge(12);
        peple.setUser("name");
        ctx.writeAndFlush(peple);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Peple peple = (Peple) msg;
        System.out.println(msg);
        System.out.println(peple.getAge());
        System.out.println(peple.getUser());
        ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        System.out.println(cause.getMessage());
        ctx.close();
    }
}

