package org.example.reflex;

import cn.hutool.core.util.ReflectUtil;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author: zyh
 * @date: 2022/11/26
 */
public class HutoolReflect {

    @Test
    public void one(){
        Method[] methods = ReflectUtil.getMethods(Human.class);
        for (Method m : methods){
            System.out.println(m);
        }

        ReflectUtil.invoke(Human.class,methods[2],null);
    }

}
