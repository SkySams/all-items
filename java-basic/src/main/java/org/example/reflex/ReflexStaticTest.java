package org.example.reflex;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 静态代理
 * @author: zyh
 * @date: 2023/1/10
 */
public class ReflexStaticTest {


    @Test
    public void create(){
        ProxyClassServiceImpl implement = new ProxyClassServiceImpl(new ClassServiceImpl());
        implement.test();
    }

    @Test
    public void create_test(){
        DynamicProxy dynamicProxy = new DynamicProxy(new ClassServiceImpl());
        ClassService o  = (ClassService) dynamicProxy.getProxy();
        o.test();
    }



}


interface ClassService {
    void test();
}

class ClassServiceImpl implements ClassService{

    @Override
    public void test() {
        System.out.println("静态代理");
    }
}

class ProxyClassServiceImpl implements ClassService{

    private ClassService classService;

    public ProxyClassServiceImpl(ClassService classService) {
        this.classService = classService;
    }

    @Override
    public void test() {
        System.out.println("代理开始");
        classService.test();
        System.out.println("代理结束");
    }
}

class DynamicProxy implements InvocationHandler {

    private Object target;
    public DynamicProxy(Object target){
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理开始");
        Object object = method.invoke(target , args);
        System.out.println("动态代理结束");
        return object;
    }
    public Object getProxy(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class[] interfaces = target.getClass().getInterfaces();
        return Proxy.newProxyInstance(loader , interfaces , this::invoke);
    }

}
