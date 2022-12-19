package org.example;

import org.junit.Test;

/**
 * @author: zyh
 * @date: 2022/10/17
 */
public class BaseMain {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.version"));
        System.out.println(System.getProperty("java.vm.version"));
        System.out.println(System.getProperty("user.name"));
    }

    @Test
    public void main() {
        System.out.println(insertString("helloworldsadweq", "/r/n", 3));
    }

    public static String inss(String ors, char ins, int count) {
        String regex = "(.{" + count + "})";
        ors = ors.replaceAll(regex, "$1" + ins);
        ors = ors.charAt(ors.length() - 1) == ins ? ors.substring(0, ors.length() - 1) : ors;
        return ors;
    }

    public static String insertString(String ors, String ins, int count) {

        StringBuilder sb = new StringBuilder(ors);    //将String转为StringBuilder
        int times = (ors.length() + count - 1) / count - 1;            //计算出有多少个位置需要插入
        for (int i = times; i >= 1; i--) {
            sb.insert(i * count, ins);                //需要从字符串的末尾开始插入，如果从前面插入，还需要计算后面的位置
        }
        return sb.toString();
    }


}
