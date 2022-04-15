package org.example.design.factory.adaptation.objectadaptation;

/**
 * @author: zyh
 * @date: 2022/4/15
 * 适配器
 */
public class DuckAdapter extends SuperDuck  {
    private SuperDuck superDuck;

    public DuckAdapter(SuperDuck superDuck) {
        this.superDuck = superDuck;
    }

    public void supFly(){
        superDuck.operation();
    }
}
