package org.example.practice;

import org.junit.Test;

import java.util.*;

/**
 * @author: zyh
 * @date: 2023/3/31
 */
public class NastationOne {

    @Test
    public void two(){
        Integer [] result =  algorithmOne(new Integer[]{2,7,10,1,11},9);
        for (Integer i : result){
            System.out.println(i);
        }

    }

    /**
     * 算法 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回它们的数组下标
     * @return
     */
    public Integer[] algorithmOne (Integer [] nums,int target){
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            int n = target - nums[i];
            if (map.containsKey(n)){
                return new Integer[]{map.get(n), i};
            } else {
                map.put(nums[i],i);
            }
        }
        return null;
    }


    @Test
    public void one(){
        int [] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(this.maxSubArray(nums));
    }

    public int maxSubArray(int[] nums) {
        int res = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum > 0) {
                sum += num;
            }
            else {
                sum = num;
            }
            res = Math.max(res, sum);
        }
        return res;
    }




    /**
     * 一个整数，它加上100后是一个完全平方数，再加上168又是一个完全平方数，请问该数是多少？
     * 1、则：x + 100 = n2, x + 100 + 168 = m2
     * 2、计算等式：m2 - n2 = (m + n)(m - n) = 168
     * 3、设置： m + n = i，m - n = j，i * j =168，i 和 j 至少一个是偶数
     * 4、可得： m = (i + j) / 2， n = (i - j) / 2，i 和 j 要么都是偶数，要么都是奇数。
     * 5、从 3 和 4 推导可知道，i 与 j 均是大于等于 2 的偶数。
     * 6、由于 i * j = 168， j>=2，则 1 < i < 168 / 2 + 1。
     * 7、接下来将 i 的所有数字循环计算即可。
     */
    @Test
    public void perfectSquare() {
        int o = 1000;
        int p = 100;
        int u = 168;
        for (int i = 2; i < o; i++) {
            int num = i + p;
            double result = Math.sqrt(num);
            if (num % result == 0) {
                num += u;
                result = Math.sqrt(num);
                if (num % result == 0) {
                    System.out.println("i=" + i);
                }
            }
        }


        for (int i = 1; i < (168 / 2 + 1); i++) {
            if (168 % i == 0) {
                int j = 168 / i;
                if (i > j && (i + j) % 2 == 0 && (i - j) % 2 == 0) {
                   int m = (i + j) / 2;
                   int n = (i - j) / 2;
                   int x = n * n - 100;
                    System.out.println(""+x+" : "+n+" : "+n);;
                    System.out.println(""+x+" : "+m+" : :"+m);
                }
            }
        }

    }


    static final double SW = 100000;
    static final double ESW = 200000;
    static final double SSW = 400000;
    static final double LSW = 600000;
    static final double YBW = 1000000;


    /**
     * 企业发放的奖金根据利润提成。
     * 利润(I)低于或等于10万元时，奖金可提10%；
     * 利润高于10万元，低于20万元时，低于10万元的部分按10%提成，高于10万元的部分，可提成7.5%；
     * 20万到40万之间时，高于20万元的部分，可提成5%；
     * 40万到60万之间时高于40万元的部分，可提成3%；
     * 60万到100万之间时，高于60万元的部分，可提成1.5%；
     * 高于100万元时，超过100万元的部分按1%提成。
     */

    @Test
    public void count_two() {
        double profix = 555555;
        if (profix <= SW) {
            System.out.println("奖金可提10%");
        } else if (SW < profix && profix < ESW) {
            System.out.println("奖金可提7.5%");
        } else if (ESW < profix && profix < SSW) {
            System.out.println("可提成5%");
        } else if (SSW < profix && profix < LSW) {
            System.out.println("可提成3%");
        } else if (LSW < profix && profix < YBW) {
            System.out.println("可提成1.5%");
        } else if (YBW < profix) {
            System.out.println("可提成1%");
        }
    }


    /**
     * 有 1、2、3、4 四个数字，能组成多少个互不相同且无重复数字的三位数？都是多少？
     */

    @Test
    public void count_one() {
        Set<Integer> set = new HashSet<>();
        for (int x = 1; x < 5; x++) {

            for (int y = 1; y < 5; y++) {

                for (int i = 1; i < 5; i++) {
                    if (y == x || i == y || i == x) {
                        continue;
                    }
                    String result = "" + x + y + i;
                    set.add(Integer.valueOf(result));
                }

            }

        }
        System.out.println("组成三位数不重复的数共有" + set.size());
        for (Integer num : set) {
            System.out.println("组成三位数不重复的数：" + num);
        }
    }

}
