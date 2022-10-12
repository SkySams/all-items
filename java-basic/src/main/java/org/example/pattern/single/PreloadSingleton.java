package org.example.pattern.single;

/**
 * @author: zyh
 * @date: 2022/9/21
 */
public class PreloadSingleton {

    /**
        预加载：很明显，没有使用该单例对象，该对象就被加载到了内存，会造成内存的浪费。
     */
    static PreloadSingleton instance = new PreloadSingleton();

    private PreloadSingleton() {
    }

    public static PreloadSingleton getInstance() {
        return instance;
    }
}
