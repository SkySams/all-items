package org.example.hash;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**

 重写equals()，但不重写hashCode()方法

 */
public class HashCodeTest {

    public static void main(String[] args) {
        Collection set = new HashSet();
        Point p1 = new Point(1,1);
        Point p2 = new Point(1,1);

        System.out.println(p1.equals(p2));
        set.add(p1);
        set.add(p2);
        set.add(p1);
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());

        Iterator iter = set.iterator();

        while (iter.hasNext()){
            Object object= iter.next();
            System.out.println(object);
        }


    }

}


class Point{
    private int x;
    private int y;
    public Point(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

//    测试二：覆盖hashCode()，但不覆盖equals()，仍然会导致数据的不唯一性
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;


    }


//重写equals()，但不重写hashCode()方法
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        Point other = (Point) obj;
//        if (x != other.x) {
//            return false;
//        }
//        if (y != other.y) {
//            return false;
//        }
//        return true;
//    }


    @Override
    public String toString() {
        return "x:" + x + ",y:" + y;
    }
}
