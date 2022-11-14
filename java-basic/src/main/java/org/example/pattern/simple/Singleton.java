package org.example.pattern.simple;

/**
 * 懒加载
 * @author: zyh
 * @date: 2022/11/14
 */
public class Singleton {

    /**
         懒加载不浪费内存，但是无法保证线程的安全。首先，if判断以及其内存执行代码是非原子性的。
         其次，new Singleton()无法保证执行的顺序性
         volatile:１,保证可见性２,不保证原子性３,禁止指令重排
     */
    private static volatile Singleton singleton = null;

    private Singleton() {
    }

    /**
        我们把sychronized加在if(instance==null)判断语句里面，保证instance未实例化的时候才加锁
     */
    public static synchronized Singleton getInstance(){
        if (singleton == null){
            synchronized (Singleton.class){
                if (singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
