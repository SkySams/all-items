package org.example.client.example;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * 链接实例
 * @author: zyh
 * @date: 2022/3/21
 */
public class ConnectExample {

    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();

    public static void connect() {
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        // Does not block
        ChannelFuture future = channel.connect(new InetSocketAddress("localhost", 25));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) {
                if (future.isSuccess()) {
                    ByteBuf buffer = Unpooled.copiedBuffer("Hello", Charset.defaultCharset());
                    ChannelFuture wf = future.channel().writeAndFlush(buffer);
                    // ...
                } else {
                    Throwable cause = future.cause();
                    cause.printStackTrace();
                }
            }
        });

    }

    public static void main(String[] args) {
        ConnectExample.connect();
    }

}
