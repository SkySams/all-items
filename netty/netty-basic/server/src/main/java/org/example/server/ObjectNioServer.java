package org.example.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.example.entity.Peple;
import org.example.handler.EchoServerHandler;
import org.example.handler.ObjectServerHandler;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author: zyh
 * @date: 2022/3/22
 */
public class ObjectNioServer {

    private final int port;

    public ObjectNioServer(int port) {
        this.port = port;
    }

    public static void main(String[] args)
            throws Exception {
        int port = Integer.parseInt("5612");
        new ObjectNioServer(port).start();
    }


    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ObjectServerHandler());
            ChannelFuture channelFuture = bootstrap.bind().sync();
            System.out.println(channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

}
