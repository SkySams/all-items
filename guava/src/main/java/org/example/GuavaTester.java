package org.example;


import com.google.common.base.Optional;

/**
 * https://www.yiibai.com/guava/guava_table.html
 * @author: zyh
 * @date: 2022/4/18
 */
public class GuavaTester {

    public static void main(String[] args) {



    }


    public void test_two(){

    }

    public void sum_two(Optional<Integer> a, Optional<Integer> b){
        System.out.println();
    }




    static void test_one(){
        GuavaTester guavaTester = new GuavaTester();

        Integer invalidInput = null;
        Optional<Integer> a =  Optional.of(invalidInput);
        Optional<Integer> b =  Optional.of(new Integer(10));
        System.out.println(guavaTester.sum_one(a,b));
    }

    public Integer sum_one(Optional<Integer>  a, Optional<Integer> b){
        return a.get() + b.get();
    }
}
