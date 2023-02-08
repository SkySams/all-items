package org.example.design.adaptation.socket.obj;

import org.example.design.adaptation.socket.Socket;

/**
 * @author: zyh
 * @date: 2023/2/8
 */
public class ThreadLavelSocket implements Socket {
    @Override
    public void contact() {
        System.out.println("三级插座......");
    }
}
