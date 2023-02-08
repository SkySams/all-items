package org.example.design.adaptation.socket.cla;

import org.example.design.adaptation.socket.AbstractSocket;

/**
 * 三级
 * @author: zyh
 * @date: 2023/2/8
 */
public class SecondLevelSocket extends AbstractSocket {
    @Override
    public void contact() {
        System.out.println("二级插座");
    }
}
