package org.example.seriali;

import org.junit.Test;

import java.io.*;

/**
 * @author: zyh
 * @date: 2023/2/6
 */
public class SerializeTest {

    /**

     最后再补充两点， 哪些字段是不能被序列化的呢？
     1. static 修饰的， 因为序列化操作是对于 堆 区 ，而static的在全局区
     2.transient 修饰的字段 ，在使用implements Serializable 的时候，也是避开序列化的

     */


    /**
     * 序列化
     * @throws IOException
     */
    @Test
    public void serialize() throws IOException{
        //序列化对象-IO流-存储
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\workspace\\dogInfoText.out"));

        Dog dog = new Dog();
        dog.setName("阿福");
        dog.setAge(1);

        objectOutputStream.writeObject(dog);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * 反序列化
     */
    @Test
    public void deserialization()throws IOException, ClassNotFoundException{
        //序列化对象-IO流-存储
        ObjectInputStream objectOutputStream = new ObjectInputStream (new FileInputStream("D:\\workspace\\dogInfoText.out"));
        Dog dog = (Dog)objectOutputStream.readObject();
        System.out.println("dog's name:"+dog.getName());
        System.out.println(dog);
    }

}
