package org.example.generic.bridge;

/**
 * @author: zyh
 * @date: 2022/8/8
 */
public class BridgeMethod {

    public static void main(String[] args) {
        MyNode mn = new MyNode(5);
        ((Node) mn).setData("hello");
    }

}
