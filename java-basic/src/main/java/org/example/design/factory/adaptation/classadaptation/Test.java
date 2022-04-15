package org.example.design.factory.adaptation.classadaptation;

/**
 * @author: zyh
 * @date: 2022/4/15
 */
public class Test {

    /**
     目标接口（Target）：客户所期待的接口。目标可以是具体的或抽象的类，也可以是接口。
     需要适配的类（Adaptee）：需要适配的类或适配者类。
     适配器（Adapter）：通过包装一个需要适配的对象，把原接口转换成目标接口。
     */

    public static void main(String[] args) {
        // 使用普通功能类
        Target concreteTarget = new ConcreteTarget();
        concreteTarget.request();

        // 使用特殊功能类，即适配类
        Target adapter = new Adapter();
        adapter.request();
    }


}
