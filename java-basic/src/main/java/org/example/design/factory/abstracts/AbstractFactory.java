package org.example.design.factory.abstracts;

/**
 * @author: zyh
 * @date: 2022/4/15
 */
public interface AbstractFactory {

    /**
     * 制造发动机
     * @return
     */
    Engine createEngine();

    /**
     * 制造空调
     * @return
     */
    Aircondition createAircondition();

}
