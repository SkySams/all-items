package org.example.client;

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


}
