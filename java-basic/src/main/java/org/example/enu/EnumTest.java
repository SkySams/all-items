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
        System.out.println(School.NU.equals("NU"));
        System.out.println(School.valueOf(School.class,"KO"));
    }

}

enum School{
    NU,KO
}
