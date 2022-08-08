package org.example.generic;

import lombok.Data;

/**
 * 泛型类
 * @author: zyh
 * @date: 2022/8/6
 */
public class Box <T>{

    private T t;

    public void set(T t){
        this.t = t;
    }

    public T get(){
        return t;
    }


}
