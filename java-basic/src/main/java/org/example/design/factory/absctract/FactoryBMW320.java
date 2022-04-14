package org.example.design.factory.absctract;

import org.example.design.factory.single.BMW;
import org.example.design.factory.single.BMW320;

/**
 * @author: zyh
 * @date: 2022/4/13
 */
public class FactoryBMW320 implements FactoryBMW{
    @Override
    public BMW createBMW() {
        return new BMW320();
    }
}
