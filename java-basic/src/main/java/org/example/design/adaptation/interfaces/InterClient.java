package org.example.design.adaptation.interfaces;

/**
 * @author: zyh
 * @date: 2022/6/6
 */
public class InterClient {

    public static void main(String[] args) {
        // 接口适配器模式
        Sourceable source1 = new SourceSub1();
        Sourceable source2 = new SourceSub2();

        source1.method1();
        source1.method2();
        source2.method1();
        source2.method2();

    }

}
