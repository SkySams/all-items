package org.example.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.example.entity.Peple;
import org.example.handler.EchoServerHandler;
import org.example.handler.ObjectServerHandler;

import java.net.InetSocketAddress;

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
        final ObjectServerHandler serverHandler = new ObjectServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(serverHandler);
                        }
                    });

            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() +
                    " started and listening for connections on " + f.channel().localAddress());
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

}
