package org.example.reflex;

/**
 * 反射
 *
 * @author: zyh
 * @date: 2022/5/16
 *
 * 通过java语言中的反射机制可以操作字节码文件（可以读和修改字节码文件。）
 * 通过反射机制可以操作代码片段。（class文件。）
 */
public class TestReflexClass {

    /**
     * java.lang.Class 代表整个字节码，代表一个类型，代表整个类
     * java.lang.reflect.Method 代表字节码中的方法字节码，代表类中的方法
     * java.lang.reflect.Constructor 代表字节码中的构造函数，代表类中的构造方法
     * java.lang.reflect.field 代表字节码中的属性字节码，代表类中的成员变量（静态变量+实例变量）
     */

    /**
     * 获取class 的三种方式
     * Class.forName("完整类名带包名")  静态方法
     * 对象.getClass()
     * 任何类型。classs
     */

    // 通过反射方式实例对象
    /**
     * 对象.newInstance()
     * newInstance()方法内部实际上调用了无参数构造方法，必须保证无参构造存在才可以。否则会抛出java.lang.InstantiationException异常
     */

    /**
     * JDBC重点(Class.forName导致类加载)
     * 如果你只希望class的静态方法执行， 其他代码一律不执行，就使用class.forName("完整类名带报名")： Class.forName("完整")
     * 这个方法的执行会导致类加载，类加载时，静态代码块执行。
     */

    /**
     * Class 类方法
     *
     * public int newInstance()         创建对象
     * public String getName()          返回完成类名带包名
     * public String getSimpleName()    返回类名
     * public Field[] getFields()       返回类中public修饰的方法
     * public Field[] getDeclaredFields() 返回类中的所用属性
     * public Field[] getDeclaredFields() 根据属性名name获取指定的属性
     * pubiic native int getModifiers()  获取属性的修饰符列表,返回的修饰符是一个数字，每个数字是修饰符的代号【一般配合Modifier类的toString(int x)方法使用】
     * public Method[] getDeclaredMethod() 返回类中所有的实例方法
     * public Constructor<?>[] getDeclaredConstructors() 返回类中所有的构造方法
     * public Constructor getDeclaredConstructor(Class<?>… parameterTypes) 根据方法形参获取指定的构造方法
     * public native Class<? super T> getSuperclass()	返回调用类的父类
     * public Class<?>[] getInterfaces()	返回调用类实现的接口集合
     *
     *
     * @param args
     */

    public static void main(String[] args) {
        try {
            // 通过反射机制，获取class,通过newInstance实例化对象
            Class cla = Class.forName("org.example.reflex.MyReflex");
            // newInstance() 这个方法会调用MyReflex这个类的无参数构造方法，完成对象的创建。
            // 重点是：newInstance()调用的是无参构造，必须保证无参构造是存在的！
            Object c = cla.newInstance();
            if (c instanceof MyReflex){
                System.out.println(((MyReflex) c).getNo());
            } else {
                System.out.println("并不是");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
