package org.example.enu;

import org.junit.Test;

/**
 *
 * @author: zyh
 * @date: 2022/3/5
 */
public class EnumTest {

    @Test
    public void one(){
        System.out.println(School.KO.name());
    }

}

enum School{
    NU,KO
}
