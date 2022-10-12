package org.example.test.proxy;

import org.example.pattern.proxy.one.IvoryTower;
import org.example.pattern.proxy.one.Wizard;
import org.example.pattern.proxy.one.WizardTower;
import org.example.test.proxy.utils.InMemoryAppender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author: zyh
 * @date: 2022/10/12
 */
public class IvoryTowerTest {

    private InMemoryAppender appender;

    @BeforeEach
    public void setUp(){
        appender = new InMemoryAppender(IvoryTower.class);
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
                new Wizard("Merlin") );

        WizardTower tower = new IvoryTower();
        wizards.forEach(tower::enter);

        assertTrue(appender.logContains("Gandalf enters the tower."));
        assertTrue(appender.logContains("Dumbledore enters the tower."));
        assertTrue(appender.logContains("Oz enters the tower."));
        assertTrue(appender.logContains("Merlin enters the tower."));
        assertEquals(4, appender.getLogSize());
    }

}
