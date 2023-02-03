package org.example.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zyh
 * @date: 2023/2/3
 */
public class StringBasi {

    List<String> list = new ArrayList<>();

    @Test
    public void main(){
        this.init();
        System.out.println("未移除前: "+list.toString());
        list.remove("C");
        list.add("D");
        System.out.println("移除后: "+list.toString());
    }

    @Test
    public void removeif(){
        this.init();
        System.out.println("未移除前" + list);
        list.removeIf(o-> "C".equals(o));
        System.out.println("移除后" + list);
    }

    @Test
    public void case_one(){
        List<String> list = new ArrayList();
        list.add("A");
        list.add("C");
        list.add("C");
        System.out.println("未移除前" + list);
        for (int i = 0; i < list.size(); i++) {
            if ("C".equals(list.get(i))) {
                list.remove("C");
            }
        }
        System.out.println("移除后" + list);
    }


    @Test
    public void remove_one(){
        this.init();
        System.out.println("未移除前" + list.toString());
        for (int i = 0; i < list.size(); i++) {
            System.out.println("size: "+list.size());
            System.out.println(i);
            if ("C".equals(list.get(i))) {
                list.remove("C");
            }
        }
        System.out.println("移除后" + list.toString());
    }

    @Test
    public void remove(){
        this.init();
        System.out.println("未移除前" + list.toString());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if ("C".equals(list.get(i))){
                list.remove("C");
            }
        }
        System.out.println("移除后" + list.toString());
    }

    @Test
    public void forremore(){
        this.init();
        for (String str : list){
            if ("C".equals(str)){
                list.remove(str);
            }
        }
    }

    public void init (){
        list.add("C");
        list.add("A");
        list.add("C");
        list.add("C");
        list.add("B");
        list.add("F");
        list.add("C");
        list.add("C");
    }
}
