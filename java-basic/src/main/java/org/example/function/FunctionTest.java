package org.example.function;


import java.util.function.Consumer;

/**
 * @author: zyh
 * @date: 2022/8/17
 */
public class FunctionTest {

    public static void main(String[] args) {
        getConsumer("hello world",(x) -> {
            for (int i=0;i<100;i++){
                System.out.println(i);
                System.out.println(x);
            }
        });
    }


    public static void getConsumer(String str,  Consumer<String> consumer){
        consumer.accept(str);
    }

}
