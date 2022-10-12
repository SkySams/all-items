package org.example.test.proxy;

import org.example.pattern.proxy.one.AppProxy;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author: zyh
 * @date: 2022/10/12
 */
public class AppTest {


    @Test
    void shouldExecuteApplicationWithoutException(){
        assertDoesNotThrow(() -> AppProxy.main(new String[]{}));
    }

}
