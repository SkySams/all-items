package org.example.reflex;

import org.junit.Test;

import java.io.FileReader;
import java.util.*;

/**
 * https://blog.csdn.net/qq_44715943/article/details/120587716
 *
 * @author: zyh
 * @date: 2022/5/17
 */
public class TestReflex {

    public TestReflex(){}


//        AtomicReference<BigDecimal> storeMoney = new AtomicReference<>(BigDecimal.ZERO);
//        storeMoney.set(BigDecimal.valueOf(12));
//        storeMoney.set(BigDecimal.valueOf(10).add(storeMoney.get()));
//        System.out.println(storeMoney.get());

//        List<User> list = new ArrayList<>();
//        list.add(new User(23));
//        list.add(new User(23));
//        Long count = list.stream().collect(Collectors.summingLong(User::getNum));
//        System.out.println(count);


    /**
     * test
     *
     * @param args
     */
    public static void main(String[] args) {

//        List<User> list = new ArrayList<>();
//        list.add(new User(23));
//        list.add(new User(23));
//         Iterator<User> iter = list.iterator();
//         while (iter.hasNext()){
//            User sd = iter.next();
//            sd.setNum(12);
//         }
//        System.out.println(list.size());
//        Long count = list.stream().collect(Collectors.summingLong(User::getNum));
//        System.out.println(count);


        Set<String> set1 = new HashSet<>();
        set1.add("1");

        Set<String> set2 = new HashSet<>();
        set2.add("1");
        set2.add("2");
        set2.add("3");


        System.out.println(set2.contains("1"));
        long count =set2.stream().filter(in -> !set1.contains(in)).count();
        System.out.println(count);
        TestReflex  estReflex = new TestReflex();
        estReflex.test_two();

    }


    @Test
    public void test_five(){
        ResourceBundle bundle = ResourceBundle.getBundle("javase/reflectBean/db");
        String className = bundle.getString("className");
        System.out.println(className);

    }


    /**
     *  如果你只是希望一个类的静态代码块执行，其它代码一律不执行，
     *  你可以使用：
     *  Class.forName("完整类名");
     */
    @Test
    public void test_four(){
        try {
            Class.forName("org.example.reflex.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test_three() throws Exception {
        // 以下代码是灵活的，代码不需要改动，可以修改配置文件，配置文件修改之后，可以创建出不同的实例对象。
        // 通过IO流读取reflectClassInfo.properties文件
        FileReader reader = new FileReader("D:\\workspace\\all-items\\java-basic\\src\\main\\java\\reflectClassInfo.properties");
        // 创建属性类对象Map
        // key value都是String
        Properties pro = new Properties();
        //加载
        pro.load(reader);
        reader.close();
        // 通过key获取value
        String className = pro.getProperty("MyReflex");
        // 通过反射机制实例化对象
        Class c = Class.forName(className);
        Object obj = c.newInstance();
        System.out.println(obj);


    }

    /**
     * 获取到Class，能干什么？
     * 通过Class的newInstance()方法来实例化对象。
     * 注意：newInstance()方法内部实际上调用了无参数构造方法，必须保证无参构造存在才可以。
     */
    @Test
    public void test_two() {
        try {
            Class cla = Class.forName("org.example.reflex.MyReflex");
//            Class cla = Class.forName("org.example.reflex.TestReflex", true, this.getClass().getClassLoader());
            Object obj = cla.newInstance();
            MyReflex myReflex = new MyReflex();
            System.out.println(myReflex);
            System.out.println(obj);
            System.out.println(cla);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 简答测试
     */
    public static void test_one() {
        try {


            Class c1 = Class.forName("java.lang.String");
            Class c2 = Class.forName("java.lang.Integer");
            Class c3 = Class.forName("java.util.Date");


            String a = "abc";
            Class c4 = a.getClass();
            System.out.println(c4);
            System.out.println(c4 == c1);


            Date time = new Date();
            Class c5 = time.getClass();
            System.out.println(c5 == c3);

            Class i = Integer.class;
            Class d = Double.class;
            Class f = Float.class;
            System.out.println(i == c2);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}


class User {
    public User() {
    }

    public User(Integer num) {
        this.num = num;
    }

    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
