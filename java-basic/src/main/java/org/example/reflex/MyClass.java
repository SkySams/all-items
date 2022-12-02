package org.example.reflex;

import org.junit.Test;

import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author: zyh
 * @date: 2022/11/29
 */
public class MyClass {

    @Test
    public void one()throws  Exception{
       Class cla = Class.forName("org.example.reflex.User");
       User obj = (User)cla.newInstance();
    }

    @Test
    public void two() throws Exception{
        FileReader reader = null;
        reader = new FileReader("D:\\workspace\\all-items\\java-basic\\src\\main\\resources\\info.properties");
        Properties properties = new Properties();
        properties.load(reader);
        reader.close();

        String path = properties.getProperty("userClassName");
        System.out.println(path);
        Class cla = Class.forName(path);
        Object obj = cla.newInstance();
        System.out.println(obj);
    }

    @Test
    public void three()throws Exception{
        String path = Thread.currentThread().getContextClassLoader().getResource("D:\\workspace\\all-items\\java-basic\\src\\main\\resources\\info.properties").getPath();
        Properties properties = new Properties();
        properties.load(new FileReader(path));
        System.out.println(Class.forName(properties.getProperty("userClassName")).newInstance());
    }

    /**
     * 以流的形式返回：
     * @throws Exception
     */
    @Test
    public void four()throws Exception{
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("info.properties");
        Properties properties = new Properties();
        properties.load(in);
        System.out.println(Class.forName(properties.getProperty("userClassName")).newInstance());
    }

    /**
     * 资源绑定器 ResourceBundle
     * @throws Exception
     */
    @Test
    public void five() throws Exception{
        ResourceBundle bundle = ResourceBundle.getBundle("info");		// 扩展名不能写！！！
        String userClassName = bundle.getString("userClassName");
        System.out.println(Class.forName(userClassName).newInstance());

    }

    /**
     * Field
     *  获取属性字节码
     * @throws Exception
     */
    @Test
    public void six() throws Exception{
        Class<User> userClass = User.class;
        Field[] fields = userClass.getFields();
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            // String toString(int mod)：返回描述指定修饰符中的访问修饰符标志的字符串
            String modifiersStr = Modifier.toString(modifiers);
            System.out.println(modifiersStr + "\t" + field.getType().getSimpleName() + "\t" + field.getName());
        }
        /* 输出：
         *		public	Integer	id
         *		public static final	double	MATH_PI
         */

    }

    @Test
    public void seven()throws Exception{
        Class<User> userClass = User.class;
        Field[] declaredFields = userClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            int modifiers = declaredField.getModifiers();
            // String toString(int mod)：返回描述指定修饰符中的访问修饰符标志的字符串
            String modifiersStr = Modifier.toString(modifiers);
            System.out.println(modifiersStr + "\t" + declaredField.getType().getSimpleName() + "\t" + declaredField.getName());
        }

        /* 输出：
         *		public	Integer	id
         *		protected	Integer	age
         *		private	String	name
         *		boolean	sex
         *		public static final	double	MATH_PI
         */
    }

    /**
     * 访问对象属性
     * @throws Exception
     */
    @Test
    public void eight() throws Exception{
        Class userClass = Class.forName(ResourceBundle.getBundle("info").getString("userClassName"));
        Object user = userClass.newInstance();

        // 返回一个 User 类的指定字段的 Field 对象
        Field age = userClass.getDeclaredField("age");

        // 给 User 对象的 age 属性赋值
        age.set(user, 22);
        // 获取 User 对象的 age 属性的值
        System.out.println(age.get(user));

        // name 属性是私有的，无法访问
        Field name = userClass.getDeclaredField("name");
        // 值为 true 表示反射对象在使用时应禁止 Java 语言访问检查。
        name.setAccessible(true);
        name.set(user, "张三");
        System.out.println(name.get(user));

    }

    @Test
    public void nine() throws Exception{
        Class userService = Class.forName("org.example.reflex.UserService");
        Method[] methods = userService.getDeclaredMethods();
        for (Method method : methods) {
            String modifier = Modifier.toString(method.getModifiers());
            String returnType = method.getReturnType().getSimpleName();
            String name = method.getName();
            System.out.print(modifier + " " + returnType + " " + name + " ");
            Class[] parameterTypes = method.getParameterTypes();
            for (Class parameterType : parameterTypes) {
                System.out.print(parameterType.getSimpleName() + " ");
            }
            System.out.println();
        }

    }

    @Test
    public void ten()throws Exception{
        Class userService = Class.forName("org.example.reflex.UserService");
        Object obj = userService.newInstance();

        Method login = userService.getDeclaredMethod("login", String.class, String.class);
        Object retVal = login.invoke(obj, "admin", "123");
        System.out.println(retVal);

        Method logout = userService.getDeclaredMethod("logout");
        Object logoutRetVal = logout.invoke(obj);
        System.out.println(logoutRetVal);

    }

    @Test
    public void elevent() throws Exception{
        Class clazz  = Class.forName("org.example.reflex.UserService");
        Constructor allArgsConstr = clazz.getDeclaredConstructor(Integer.class, Integer.class, String.class, boolean.class);
        Object user = allArgsConstr.newInstance(1, 22, "张三", true);
        System.out.println(user);
        Constructor noArgsConstr = clazz.getDeclaredConstructor();
        Object obj = noArgsConstr.newInstance();
        System.out.println(obj);

    }

    @Test
    public void person()throws Exception{
        Class clazz = Class.forName("java.lang.String");
        Class superclass = clazz.getSuperclass();
        // 获取 String 的父类
        System.out.println(superclass.getSimpleName());
        // 获取 String 实现的接口
        Class[] interfaces = clazz.getInterfaces();
        for (Class anInterface : interfaces) {
            System.out.println(anInterface.getSimpleName());
        }

    }

}
