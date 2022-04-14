package org.example.design.factory.single;

import org.example.design.factory.enums.BMWType;

/**
 * @author: zyh
 * @date: 2022/4/13
 */
public class FactoryBMW {

    /**
     * 工厂类角色： 该模式的核心，用来创建产品，含有一定的商业逻辑和判断逻辑
     *
     * @param type
     * @return
     */

    public static BMW createBMW(BMWType type){
        if (type.equals(BMWType.BMW_320)){
            return new BMW320();
        }
        if (type.equals(BMWType.BMW_520)){
            return new BMW520();
        }
        return null;
    }

}
