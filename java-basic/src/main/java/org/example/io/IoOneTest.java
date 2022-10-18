package org.example.io;

import org.example.hutool.proxy.Cat;

import java.io.*;
import java.nio.file.Path;
import java.util.Properties;

/**
 * @author: zyh
 * @date: 2022/10/17
 */
public class IoOneTest {

    final static String path = "C:\\Users\\Administrator\\Desktop\\金刚区图标动效\\hello.txt";


    public static void main(String[] args) throws Exception {
        porpretist();
    }

    public static void one() throws IOException {

        File file = new File("C:\\Users\\Administrator\\Desktop\\金刚区图标动效");
        System.out.println(file.canExecute());
        System.out.println(file.getName());
        System.out.println(file.getPath());
//        String [] files = file.list();
//        for (String str : files){
//            System.out.println(str);
//        }

        Path path = file.toPath();
        System.out.println(path.getName(1));
        System.out.println();

    }

    public static void two() throws IOException {
        File file = new File(path);
        if (file.canExecute()){
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write("hello world".getBytes());
            fileOutputStream.close();
        } else {
            boolean sta =  file.createNewFile();
            System.out.println(sta);
        }

    }

    public static void three() throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(path),"GBK");
        outputStreamWriter.write("汉字");
        outputStreamWriter.flush();
        outputStreamWriter.close();
    }

    /**
     * 此类不是一个流，是Object的子类
     * 融合了InputStream和OutputStream
     * 支持对文件随机访问读取和写入
     */
    public static void four() throws Exception {
        RandomAccessFile raf = new RandomAccessFile(path,"rw");
        raf.write("1234567890".getBytes(), 0, 10);
        System.out.println("当前文件指针位置"+raf.getFilePointer());
//        raf.seek(4);//设置指针位置
//        char c = raf.readChar();
//        System.out.println(c);
//        System.out.println("当前文件指针位置"+raf.getFilePointer());

    }

    /**
     * 序列化流反序列化流
     */
    public static void five()throws Exception{
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
        Object o = objectInputStream.readObject();
        System.out.println(o);
        Cat s = (Cat) o;
        s.eat();
        objectInputStream.close();
    }

    public static void six()throws Exception{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
        Cat student = new Cat();
        objectOutputStream. writeObject(student);
        objectOutputStream.close();
    }


    /**
     * 属性
     */
    public static void porpreties() throws Exception{
        Properties p = new Properties();
        Writer writer = new FileWriter(path, true);
        p.setProperty("1","a");
        p.setProperty("2","b");
        p.setProperty("3","c");

        p.store(writer,"hero");
        writer.close();
    }

    public static void porpretist()throws Exception{
        Properties p = new Properties();
        Reader reader = new FileReader(path);
        p.load(reader);
        System.out.println(p);
        System.out.println(p.getProperty("1"));
        System.out.println(p.getProperty("吕布"));
        reader.close();
    }



}
