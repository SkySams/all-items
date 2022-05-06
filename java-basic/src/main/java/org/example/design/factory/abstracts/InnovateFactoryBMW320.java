package org.example.design.factory.abstracts;

/**
 * @author: zyh
 * @date: 2022/4/15
 */
public class InnovateFactoryBMW320 implements AbstractFactory{
    /**
     * 制造发动机
     *
     * @return
     */
    @Override
    public Engine createEngine() {
        return new EngineB();
    }

    /**
     * 制造空调
     *
     * @return
     */
    @Override
    public Aircondition createAircondition() {
        return new AirconditionA();
    }



}
