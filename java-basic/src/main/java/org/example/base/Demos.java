package org.example.base;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author: zyh
 * @date: 2022/4/11
 */
public class Demos {

    /**
     * @Title: unicodeEncode
     * @Description: unicode编码 将中文字符转换成Unicode字符
     * @param string
     * @return
     */
    public static String unicodeEncode(String string) {
        char[] utfBytes = string.toCharArray();
        String unicodeBytes = "";
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }

    /**
     * @param string
     * @return 转换之后的内容
     * @Title: unicodeDecode
     * @Description: unicode解码 将Unicode的编码转换为中文
     */
    public static String unicodeDecode(String string) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(string);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            string = string.replace(matcher.group(1), ch + "");
        }
        return string;
    }

    public static void test(){
        String str = "真香IT";
        // 加密 中文 -> Unicode
        String unicodeEncode = unicodeEncode(str);
        System.out.println(str + " ---> " + unicodeEncode);
        // 解密 Unicode -> 中文
        String zh_str = unicodeDecode(unicodeEncode);
        System.out.println(unicodeEncode + " ---> " + zh_str);
    }



    public static void main(String[] args) {

//        test();
        System.out.println(unicodeDecode("\\u5546\\u54c1\\u5df2\\u7ecf\\u5df2\\u6dfb\\u52a0\\u5230\\u4e86\\u79d2\\u6740\\u6d3b\\u52a8\\u4e2d\\uff0c\\u63d0\\u4ea4\\u5931\\u8d25"));
        System.out.println(unicodeEncode("商品已经已添加到了秒杀活动中，提交失败"));

//            Set<Integer> set1 = new HashSet<>();
//            set1.add(1);
//            set1.add(2);
//            set1.add(3);
//
//            Set<Integer> set2 = new HashSet<>();
//            set2.add(1);
//            System.out.println(  set1.stream().filter(in -> !set2.contains(in)).count());

//            Map<String,String> map = new HashMap<String,String>();
//            map.put("null",null);
//            map.put(null,"nice");
//
//            System.out.println(map.containsKey("null"));
//
//           Set<Map.Entry<String,String>> set = map.entrySet();
//           for (Map.Entry entry : set){
//                   System.out.println(entry.getValue());
//           }



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
