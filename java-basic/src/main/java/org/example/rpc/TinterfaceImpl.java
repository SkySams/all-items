package org.example.rpc;

/**
 * @author: zyh
 * @date: 2023/1/9
 */
public class TinterfaceImpl implements Tinterface {
    @Override
    public String send(String msg) {
        return "send message " + msg;
    }
}