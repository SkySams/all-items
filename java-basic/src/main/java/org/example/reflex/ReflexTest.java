package org.example.reflex;

import org.junit.Test;

/**
 * @author: zyh
 * @date: 2022/11/25
 */
public class ReflexTest {

    static  {
        System.out.println("静态代码块类加载的时候执行，并且只执行一次。");
    }

    @Test
    public void reflex() throws Exception {
        Class cla = Class.forName("org.example.reflex.ReflexTest");
    }

    @Test
    public void reflexOne(){
        // 任何对象都有getClass方法
        String s = "hello world";
        Class c = s.getClass();
//        类型不同，不可==比较
//        System.out.println(c == s);
        String str = new String("hello world");
    }

    @Test
    public void reflexTwo()throws Exception{

        /**
         * 所有对象的字节码载入jvm只有一份
         */
        Class stringClass1 = Class.forName("java.lang.String");
        Class stringClass2 = "abc".getClass();
        Class stringClass3 = String.class;
        System.out.println(stringClass1 == stringClass2);	// true
        System.out.println(stringClass1 == stringClass3);	// true
        System.out.println(stringClass2 == stringClass3);	// true

        Class human1 = Class.forName("org.example.reflex.Human");
        Class human2 = Human.class;
        System.out.println(human1 == human2);
    }

    /**
     * 通过无参构筑函数构造 、 必须保证无参数
     * @throws Exception
     */
    @Test
    public void  reflexThree()throws Exception {
        Class cla = Class.forName("org.example.reflex.Person");
        Object person = cla.newInstance();
        System.out.println(person);
    }


}
