package org.example.test.thread;

import org.example.pattern.thread.App;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * @author: zyh
 * @date: 2022/8/9
 */
public class AppTest {

    @Test
    public void shouldExecuteWithoutException(){
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }

}
