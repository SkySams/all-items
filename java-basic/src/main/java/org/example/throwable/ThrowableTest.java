package org.example.throwable;

import java.util.List;

/**
 * @author: zyh
 * @date: 2023/2/3
 */
public class ThrowableTest {

    public static void main(String[] args) {

        try {
            List resultList = getResultListTest(1);
            if (resultList.size() > 0) {
                System.out.println("获取到的结果是：" + resultList);
            }
        } catch (Exception e) {
            System.out.println("异常！");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
            StackTraceElement[] elements = e.getStackTrace();
            for (int i = 0; i< elements.length; i++){
                System.out.println(elements[i].getLineNumber());
            }
        }

    }

    public static List getResultListTest(int num) {
        if (num == 1){
            System.out.println("模拟检测到空,主动抛出异常");
            new NullPointerException("resultList 为空了啊");
        }
        return null;

    }

}
