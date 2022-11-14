package org.example.pattern.simple;

/**
 * 预加载
 * @author: zyh
 * @date: 2022/11/14
 */
public class PreloadSingleten {

    /**
     单例模式和线程安全
     预加载只有一条语句return instance,这显然可以保证线程安全。但是，我们知道预加载会造成内存的浪费
     */

    private static PreloadSingleten singleten = new PreloadSingleten();

    private PreloadSingleten() {
    }

    public static PreloadSingleten getInstance(){
        return singleten;
    }

}
