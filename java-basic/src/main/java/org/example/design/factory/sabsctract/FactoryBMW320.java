package org.example.design.factory.sabsctract;

import org.example.design.factory.single.BMW;
import org.example.design.factory.single.BMW320;

/**
 * @author: zyh
 * @date: 2022/4/13
 * 具体工厂 Factory：被应用程序调用以创建具体产品的对象，含有和具体业务逻辑有关的代码
 */
public class FactoryBMW320 implements FactoryBMW{
    @Override
    public BMW createBMW() {
        return new BMW320();
    }
}
