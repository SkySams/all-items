package org.example.base;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author: zyh
 * @date: 2022/4/11
 */
public class Demos {

    @Test
    public void he(){
        new String ("{}");
    }

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

    public static Date sd(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
//        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void s(){
        String type = String.valueOf(25).replace(String.valueOf(5), "");
        System.out.println(type);
        System.out.println(StrUtil.isBlank(type)? "0":type);
    }

    public static void main(String[] args) {
        s();

//        test(); [{0}]
        System.out.println(unicodeDecode("\\u6D3B\\u52A8\\u65F6\\u95F4\\u91CD\\u590D"));
        System.out.println(unicodeEncode(""));

        LocalDateTime localDateTime = LocalDateTimeUtil.of(new Date());
//        LocalDateTime offset = LocalDateTimeUtil.offset(localDateTime, 30, ChronoUnit.MINUTES);
        LocalDateTime time = localDateTime.plus(30, ChronoUnit.MINUTES);
        System.out.println(time);
        System.out.println(sd(time));

        Date lotteryCloseTime = DateUtil.offset(new Date(), DateField.MINUTE,30);
        System.out.println(lotteryCloseTime);

        ChineseDate chineseDate = new ChineseDate(2021,1,30);
//        System.out.println(chineseDate);
        System.out.println(chineseDate.getGregorianDate());

        String zodiac = DateUtil.getZodiac(Month.JANUARY.getValue(), 30);
        System.out.println(zodiac);

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
