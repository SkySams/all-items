package org.example.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: zyh
 * @date: 2022/10/10
 */
public class DemoOne {

    public static void main(String[] args) {

        String goodsPromotionType = "";
        goodsPromotionType = goodsPromotionType.replace("5","");
        goodsPromotionType = goodsPromotionType.replace("6","");
        goodsPromotionType = goodsPromotionType.replace("7","");
        System.out.println(goodsPromotionType);

        //问题现象
        Boolean condition = Boolean.FALSE;
        Double a = 0.1d;
        Double b = 0.2d;
        Double c = null;
//        Double result = condition? a*b:c;

        //尽量使用基本数据类型，避免包装数据类型的拆装包
        boolean condi = Boolean.FALSE;
        double a1 = 1d;
        double b1 = 2d;
        double c2 = 3d;

        double res = condi? a1 * b1 : c2;



//        Calendar calendar = Calendar.getInstance();
//        // 时
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        // 分
//        calendar.set(Calendar.MINUTE, 0);
//        // 秒
//        calendar.set(Calendar.SECOND, 0);
//        // 毫秒
//        calendar.set(Calendar.MILLISECOND, 0);
//
//        Date time = calendar.getTime();
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//        String format = df.format(time);
//        System.out.println(format);
    }



}
