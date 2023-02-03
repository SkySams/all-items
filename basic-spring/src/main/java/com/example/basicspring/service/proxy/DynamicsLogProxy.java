package com.example.basicspring.service.proxy;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author: zyh
 * @date: 2023/2/3
 */
public class DynamicsLogProxy implements InvocationHandler {

    Object obj;

    //绑定委托对象，并返回代理类
    public Object bind(Object obj) {
        this.obj = obj;
        //绑定该类实现的所有接口，取得代理类
        System.out.println(obj.getClass().getClassLoader());
        System.out.println(obj.getClass().getInterfaces());
        System.out.println(this);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("+静态代理LogProxy");
        System.out.println("+下单前我们做点什么.");
        System.out.println("+正在获取目前传入的参数：" + Arrays.toString(args));
        System.out.println("+正在记录.....");
        System.out.println("+准备执行主业务");
        Object res = method.invoke(obj, args);
        System.out.println("+下单后我们做点什么.");
        System.out.println("+可得到业务方法执行后结果" + res.toString());
        System.out.println("+记录.......");

        return res;


    }
}