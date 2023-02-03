package com.example.basicspring.service.proxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import java.lang.reflect.Method;


/**
 * 看CGLIB动态代理实现
 * @author: zyh
 * @date: 2023/2/3
 */
public class CglibLogProxy  implements MethodInterceptor {

    /**
     * 自定义方法：利用Enhancer类生成代理类
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getObjByEnhancer(Class<T> clazz) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        T res = (T) enhancer.create();
        return res;
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println(" +CGLIB日志代理CglibLogProxy");
        System.out.println("+CGLIB日志代理获取到执行的方法名" + method.getName());
        System.out.println("+做一些日志记录......");
        System.out.println("+做一些日志记录...");
        Object res = methodProxy.invokeSuper(o, objects);
        System.out.println("+主业务方法执行后我们做点什么.");
        System.out.println("+可得到业务方法执行后结果"+res.toString());
        System.out.println("+记录.......");
        return res;

    }
}
