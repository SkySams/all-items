package com.example.basicspring.util;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;

/**
 * @author: zyh
 * @date: 2023/2/6
 */
public class ConvertUtil {

    //将带有T的时间字符串转换成yyyy-MM-dd HH:mm:ss
    public static String convertDate(String strDate) {
        String str = "";
        try {
            String fmt = "yyyy-MM-dd HH:mm:ss";
            strDate = strDate.replace("T", " ");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(fmt);
            return dateTimeFormatter.format(dateTimeFormatter.parse(strDate));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }
    public static void main(String args[]) throws ParseException {
        String startDateTime = "2020-05-01T00:00:00";
        String s = convertDate(startDateTime);
        System.out.println(s);

    }

}
