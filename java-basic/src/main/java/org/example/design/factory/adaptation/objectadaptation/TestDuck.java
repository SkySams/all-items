package org.example.design.factory.adaptation.objectadaptation;

/**
 * @author: zyh
 * @date: 2022/4/15
 */
public class TestDuck {

    /**
      对象适配
     * @param args
     */
    public static void main(String[] args) {
        Duck duck = new HanziDuck();
        duck.operation();

        DuckAdapter duckAdapter = new DuckAdapter(new SuperDuck());
        duckAdapter.supFly();
    }

}
