package org.example.sort;

import cn.hutool.core.date.DateUtil;
import org.junit.Test;

import java.util.*;

/**
 * @author: zyh
 * @date: 2022/10/20
 */
public class Bubble {

    /**
     * 冒泡排序：上了100W的数据排序直接崩溃
     */

    static int MAX = 10000;

    @Test
    public void BubbleTest(){
        List<Integer> integers =  Arrays.asList(23,2,10,1,75,5,34);
//        integers.sort(Comparator.naturalOrder());
        Integer[] num = new Integer[MAX];
        Random random = new Random();
        for (int i=0; i<MAX;i++){
            int n  = random.nextInt(MAX);
            num[i] = n;
        }
//        System.out.println(DateUtil.now());
//        List list = Arrays.asList(num);
//        list.sort(Comparator.naturalOrder());
//        System.out.println(DateUtil.now());

        System.out.println(DateUtil.now());
        Integer[] kon = {23,12,32,1,2,24};
        insertSort(num);
        System.out.println(DateUtil.now());
//        System.out.println(list);
        for (Integer n:num){
            System.out.println(n);
        }
    }

    /**
     * 冒泡排序
     */
    public static void sortOne(Integer[] num){
       int size = num.length;
       int r = 0;
       for (int i = 0; i < size; i++){
           for (int n = i+1; n < size; n++){
                int x = num[i];
                int y = num[n];
                if (x <y){
                    int tem = x;
                    num[i] = y;
                    num[n] = tem;
                }
                r+=1;
           }
       }
        System.out.println("循环次数： "+r);
    }

    public static void sortTwo(Integer[] num){
        int size = num.length;
        int r = 0;
        for (int x = size-1; x >=0; x--){
            for (int y =x-1; y >= 0; y--){
                int i = num[x];
                int n = num[y];
                if (i <n){
                    int tem = i;
                    num[x] = n;
                    num[y] = tem;
                }
                r+=1;
            }
        }
        System.out.println("循环次数： "+r);
    }

    /**
     * 优化
     * @param num
     */
    public static void sortThree(Integer[] num){
        int size = num.length;
        int r = 0;

        for (int x = 0; x < size; x++){
            for (int y = 0; y < size-1-x; y++){
                if (num[y] > num[y+1]){
                    int tem = num[y];
                    num[y] = num[y+1];
                    num[y+1] = tem;
                }
                r+=1;
            }
        }
        System.out.println("循环次数： "+r);
    }

    //选择排序
    public static void selectSort(Integer[] num){
        int len = num.length;

        for (int x = 0; x < len-1; x++){
            int tem = num[x];
            int order = 0;
            for (int y = x+1; y < len-1; y++){
                if (tem > num[y]) {
                    tem = num[y];
                    order = y;
                }
            }
            if (tem != num[x]){
                num[order] = num[x];
                num[x] = tem;
            }
        }
    }

    //插入排序
    public static void insertSort(Integer[] num){
        for (int i =0; i< num.length; i++){
            for (int j = 0; j < i; j++){
                if (num[i] < num[j]){
                    int tem = num[i];
                    num[i] = num[j];
                    num[j] = tem;
                }
            }

        }

    }

    // 希尔排序

}
