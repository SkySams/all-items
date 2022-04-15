package org.example.design.factory.adaptation.classadaptation;

/**
 * @author: zyh
 * @date: 2022/4/15
  适配器类，继承了被适配类，同时实现标准接口
 */
public class Adapter extends Adaptee implements Target {

    @Override
    public void request() {
        super.specificRequest();
    }
}
