package org.example.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author: zyh
 * @date: 2022/3/22
 */
public class PlainOioServer {

    public static void main(String[] args) {
        try {
            PlainOioServer.start(5612);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void start(int port) throws Exception{
        final ServerSocket socket = new ServerSocket(port);
        try {
            for (;;){
                // 等待连接
                final Socket clientSocket = socket.accept();
                System.out.println("Accepted connection from"+clientSocket);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OutputStream outputStream;
                        try {
                            outputStream = clientSocket.getOutputStream();
                            outputStream.write("Hello World".getBytes(StandardCharsets.UTF_8));
                            outputStream.flush();
                            clientSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                clientSocket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
