package org.example.hash;

/**
 * @author: zyh
 * @date: 2022/6/10
 */
public class HashDemo {

    /**

     什么是HashCode
     hashCode就是对象的散列码，是根据对象的某些信息推到出的一个整数值,默认情况下是对象的存储地址。
     提高检索的倍数，主要用于散列码存储结构中快速确定对象的存储地址。如何hashTable HashMap

     */
    public static void main(String[] args) {
        int hash = 0;
        String k = "ok";
        StringBuilder sb = new StringBuilder();
        System.out.println(k.hashCode() + "  " + sb.hashCode());

        String t = new String("ok");
        StringBuilder tb = new StringBuilder();
        System.out.println(t.hashCode() + "  " + tb.hashCode());

        String g = new String("ok");
        System.out.println(g.hashCode());
    }

}
