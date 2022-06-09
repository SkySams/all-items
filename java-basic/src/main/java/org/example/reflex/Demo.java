package org.example.reflex;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author: zyh
 * @date: 2022/6/9
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        //通过反射获取Class对象
        Class stuClass = Class.forName(getValue("className"));
        //2获取show()方法
        Method m = stuClass.getMethod(getValue("methodName"));
        //3.调用show()方法
        m.invoke(stuClass.getConstructor().newInstance());
        test();
    }

    //此方法接收一个key，在配置文件中获取相应的value
    public static String getValue(String key) throws IOException {
        Properties pro = new Properties();//获取配置文件的对象
        FileReader in = new FileReader("D:\\workspace\\all-items\\java-basic\\src\\main\\java\\pro.txt");//获取输入流
        pro.load(in);//将流加载到配置文件对象中
        in.close();
        String value = pro.getProperty(key);
        System.out.println(value);
        return value; //返回根据key获取的value值
    }

    /*
     * 通过反射越过泛型检查
     * 例如：有一个String泛型的集合，怎样能向这个集合中添加一个Integer类型的值？
     */
    public static void test() throws Exception {
        ArrayList<String> strList = new ArrayList<>();
        strList.add("aaa");
        strList.add("bbb");

        //	strList.add(100);
        //获取ArrayList的Class对象，反向的调用add()方法，添加数据
        Class listClass = strList.getClass(); //得到 strList 对象的字节码 对象
        //获取add()方法
        Method m = listClass.getMethod("add", Object.class);
        //调用add()方法
        m.invoke(strList, 100);

        //遍历集合
        for (Object obj : strList) {
            System.out.println(obj);
        }
    }


}
