package org.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.example.client.handler.EchoClientHandler;
import org.example.client.handler.ObjectClientHandler;
import org.example.entity.Peple;

import java.net.InetSocketAddress;

/**
 * @author: zyh
 * @date: 2022/3/22
 */
public class EchoClientTwo extends Echo  {

    public EchoClientTwo(String host, int port) {
       super(host,port);
    }

    public static void main(String[] args) throws Exception {
        final int port = Integer.parseInt("5612");
        new EchoClientTwo("127.0.0.1", port).start();
    }

    @Override
    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress("127.0.0.1", 5612))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(new ObjectClientHandler());
                        }
                    });
            ChannelFuture f = b.connect().sync();
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

}
