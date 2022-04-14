package org.example.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: zyh
 * @date: 2022/4/11
 */
public class Demos {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","b","c");
        list = list.stream().map(s ->s ).collect(Collectors.toList());
        List<String> list2 = new ArrayList<>();
        list2.add("a");
        list2.add("b");


        List<String> finalList = list;
        list2.forEach(s ->{
          Iterator<String> ite = finalList.iterator();
          while (ite.hasNext()){
              if (ite.next().equals(s)){
                  ite.remove();
              }
          }
        });
        System.out.println(finalList);


//        Iterator<String> iterable = list.iterator();
//        while (iterable.hasNext()){
//            if (iterable.next().equals("b")){
//                iterable.remove();
//            }
//        }
//        List<String> list2 = new ArrayList<>();
//        list2.add("a");
//        list.removeAll(list2);
//        System.out.println(list.size());
//        list2.add("c");
//        list.removeAll(list2);
//        System.out.println(list.size());
    }

}
