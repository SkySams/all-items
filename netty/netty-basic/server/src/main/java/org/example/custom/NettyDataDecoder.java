package org.example.custom;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.example.entity.Peple;

/**
 * 编码
 * @author: zyh
 * @date: 2022/3/23
 */
public class NettyDataDecoder extends MessageToByteEncoder<Peple> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Peple peple, ByteBuf byteBuf) throws Exception {
        byte[] datas = peple.toString().getBytes();
        byteBuf.writeBytes(datas);
        channelHandlerContext.flush();
    }
}
