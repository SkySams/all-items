package org.example.base;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: zyh
 * @date: 2022/4/11
 */
public class Demos {

    public static void main(String[] args) {

//            Set<Integer> set1 = new HashSet<>();
//            set1.add(1);
//            set1.add(2);
//            set1.add(3);
//
//            Set<Integer> set2 = new HashSet<>();
//            set2.add(1);
//            System.out.println(  set1.stream().filter(in -> !set2.contains(in)).count());

            Map<String,String> map = new HashMap<String,String>();
            map.put("null",null);
            map.put(null,"nice");

            System.out.println(map.containsKey("null"));

           Set<Map.Entry<String,String>> set = map.entrySet();
           for (Map.Entry entry : set){
                   System.out.println(entry.getValue());
           }



//        List<String> list = Arrays.asList("a","b","c");
//        list = list.stream().map(s ->s ).collect(Collectors.toList());
//        List<String> list2 = new ArrayList<>();
//        list2.add("a");
//        list2.add("b");
//
//
//        List<String> finalList = list;
//        list2.forEach(s ->{
//          Iterator<String> ite = finalList.iterator();
//          while (ite.hasNext()){
//              if (ite.next().equals(s)){
//                  ite.remove();
//              }
//          }
//        });




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
