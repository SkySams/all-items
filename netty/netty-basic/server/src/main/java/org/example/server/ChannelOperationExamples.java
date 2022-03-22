package org.example.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author: zyh
 * @date: 2022/3/22
 */
public class ChannelOperationExamples {

    private static final Channel CHANNEL_FROM_SOMEWHERE = new NioSocketChannel();


    public static void writingToChannel(){
        Channel channel = CHANNEL_FROM_SOMEWHERE;
        ByteBuf buf = Unpooled.copiedBuffer("you data", CharsetUtil.UTF_8);
        ChannelFuture cf = channel.writeAndFlush(buf);
        // 监听器
        cf.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                if (channelFuture.isSuccess()){
                    System.out.println("Write successful");
                } else {
                    System.out.println("write error");
                    channelFuture.cause().printStackTrace();
                }
            }
        });
    }

    /**
     * 使用线程 写入数据
     */
    public static void writingToChannelFromManyThreads(){
        final Channel channel = CHANNEL_FROM_SOMEWHERE;
        final ByteBuf buf = Unpooled.copiedBuffer("your data", CharsetUtil.UTF_8);
        Runnable writer = new Runnable() {
            @Override
            public void run() {
                channel.write(buf.duplicate());
            }
        };
        Executor executor = Executors.newCachedThreadPool();
        // write in one thread
        executor.execute(writer);

        // write in another thread
        executor.execute(writer);
    }

}
