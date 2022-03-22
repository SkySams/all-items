package org.example.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author: zyh
 * @date: 2022/3/22
 */
public class NettyNioServer {

    public static void main(String[] args) {
        try {
            start(5612);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void start(int port) throws Exception{
        ByteBuf buf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer("Hello NettyNioServer",Charset.forName("UTF-8")));
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInboundHandlerAdapter(){
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            ctx.writeAndFlush(buf.duplicate()).addListener(ChannelFutureListener.CLOSE);
                        }
                    });
            ChannelFuture channelFuture = bootstrap.bind().sync();
            System.out.println(channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

}
