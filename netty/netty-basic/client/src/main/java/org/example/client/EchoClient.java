package org.example.client;

/**
 * @author: zyh
 * @date: 2022/3/21
 */
public class EchoClient extends Echo {
    public EchoClient(String host, int port) {
        super(host,port);
    }

    public static void main(String[] args)
            throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: " + EchoClient.class.getSimpleName() + " <host> <port>"
            );
            return;
        }

        final String host = args[0];
        final int port = Integer.parseInt(args[1]);
        new EchoClient(host, port).start();
    }

}
