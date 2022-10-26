package org.example.material;

import org.junit.Test;

import java.util.Objects;
import java.util.Optional;

/**
 * @author: zyh
 * @date: 2022/10/20
 */
public class OptionalTest {

    static Integer MAX_VALUE = 100;

    /**
     * 反面
     */
    @Test
    public void optionalSide(){
        Integer value = 0;
        Integer thisValue;
        if (Objects.nonNull(value)){
            thisValue = value;
        } else {
            thisValue = 23;
        }
        System.out.println(thisValue);
    }

    /**
     * 正面
     */
    @Test
    public void optionalPositive(){
        Integer value = null;
        Integer thisValue ;
        thisValue = Optional.ofNullable(value).orElse(23);
        System.out.println(thisValue);

    }

    @Test
    public void aVoid(){
        one(200);
    }


    public static void one(Integer value){

        // 反面
        Integer thisValue;
        if (Objects.nonNull(value) && value.compareTo(MAX_VALUE) > 0){
            thisValue = value;
        } else {
            thisValue = MAX_VALUE;
        }
        System.out.println("反面："+thisValue);
        // 正面
        thisValue = Optional.ofNullable(value).filter(tem -> tem.compareTo(MAX_VALUE) > 0).orElse(MAX_VALUE);

        System.out.println("正面："+thisValue);
    }

}
