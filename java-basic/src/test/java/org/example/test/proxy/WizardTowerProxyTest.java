package org.example.test.proxy;

import org.example.pattern.proxy.one.IvoryTower;
import org.example.pattern.proxy.one.Wizard;
import org.example.pattern.proxy.one.WizardTower;
import org.example.pattern.proxy.one.WizardTowerProxy;
import org.example.test.proxy.utils.InMemoryAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author: zyh
 * @date: 2022/10/12
 */
public class WizardTowerProxyTest {

    private InMemoryAppender appender;

    @BeforeEach
    public void setUp() {
        appender = new InMemoryAppender();
    }

    @AfterEach
    public void tearDown() {
        appender.stop();
    }

    @Test
    void testEnter() {
        List<Wizard> wizards = Arrays.asList(
                new Wizard("Gandalf"),
                new Wizard("Dumbledore"),
                new Wizard("Oz"),
                new Wizard("Merlin")
        );

        WizardTower proxy = new WizardTowerProxy(new IvoryTower());
        wizards.forEach(proxy::enter);

        assertTrue(appender.logContains("Gandalf enters the tower."));
        assertTrue(appender.logContains("Dumbledore enters the tower."));
        assertTrue(appender.logContains("Oz enters the tower."));
        assertTrue(appender.logContains("Merlin is not allowed to enter!"));
        assertEquals(5, appender.getLogSize());
    }

}
