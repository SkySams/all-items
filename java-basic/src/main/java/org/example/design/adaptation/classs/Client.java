package org.example.design.adaptation.classs;

import org.example.design.adaptation.Target;

/**
 * @author: zyh
 * @date: 2022/6/6
 */
public class Client {

    public static void main(String[] args) {
        // 类的适配器
        Target adapter = new Adapter();
        adapter.request();
    }

}

