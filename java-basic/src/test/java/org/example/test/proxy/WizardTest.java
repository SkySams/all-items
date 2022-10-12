package org.example.test.proxy;

import org.example.pattern.proxy.one.Wizard;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author: zyh
 * @date: 2022/10/12
 */
public class WizardTest {

    @Test
    void testToString() {
       List<String> array = Arrays.asList("Dumbledore");
       array.forEach(name -> assertEquals(name, new Wizard(name).toString()));
    }

}
