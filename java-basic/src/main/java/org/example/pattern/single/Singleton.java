package org.example.pattern.single;

/**
 * @author: zyh
 * @date: 2022/9/21
 */
public class Singleton {

    public static volatile Singleton instance = null;

    public Singleton() {
    }

    public static synchronized  Singleton getInstance() {
        if (instance == null){
            synchronized (instance){
                instance = new Singleton();
            }
        }
        return instance;
    }
}
