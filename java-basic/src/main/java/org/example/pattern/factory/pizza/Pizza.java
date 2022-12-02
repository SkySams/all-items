package org.example.pattern.factory.pizza;

/**
 * @author: zyh
 * @date: 2022/11/14
 */
public abstract class Pizza {


    abstract void bake();

    abstract void box();

    abstract void cut();

    abstract void prepare();

    public void settlement(){
        this.bake();
        this.box();
        this.cut();
        this.prepare();
    }

}
