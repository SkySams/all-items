package org.example.design.factory.absctract;

import org.example.design.factory.single.BMW;
import org.example.design.factory.single.BMW520;

/**
 * @author: zyh
 * @date: 2022/4/13
 */
public class FactoryBMW520 implements FactoryBMW{
    @Override
    public BMW createBMW() {
        return new BMW520();
    }
}
