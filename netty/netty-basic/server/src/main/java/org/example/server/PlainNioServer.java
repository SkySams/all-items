package org.example.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: zyh
 * @date: 2022/3/22
 */
public class PlainNioServer {


    public static void main(String[] args) {
        try {
            start(5612);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void start(int port) throws Exception{
        ServerSocketChannel serverChannel  = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket serverSocket = serverChannel.socket();

        InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
        serverSocket.bind(inetSocketAddress);
        Selector selector = Selector.open();

        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        final ByteBuffer msg = ByteBuffer.wrap("Hello World!\r\n".getBytes());
        for (;;){
            try {
                // 等待链接
                selector.select();
            }catch (Exception e){
                e.printStackTrace();
                break;
            }

            Set<SelectionKey>  readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();

            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();

                try{
                    if (key.isAcceptable()){
                        ServerSocketChannel  server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector,SelectionKey.OP_WRITE|SelectionKey.OP_READ,msg.duplicate());
                        System.out.println("Accepted connection from : "+client);
                    }
                    if (key.isWritable()){
                        SocketChannel client = (SocketChannel ) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment();
                        while (buffer.hasRemaining()){
                            if (client.write(buffer) <= 0){
                                break;
                            }
                        }
                        client.close();
                    }
                } catch (Exception e){
                    key.channel();
                    try {
                        key.channel().close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        }
    }

}
