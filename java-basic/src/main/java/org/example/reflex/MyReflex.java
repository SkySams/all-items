package org.example.reflex;

/**
 * 反射
 * @author: zyh
 * @date: 2022/5/16
 */
public class MyReflex {

    /**
     * field
     */
    private int no = 100;

    public MyReflex() {
    }

    public MyReflex(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String edit(String name){
        return name;
    }
}
