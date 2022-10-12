package org.example.test.proxy.utils;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/10/12
 */
public class InMemoryAppender extends AppenderBase<ILoggingEvent> {

    private final List<ILoggingEvent> log = new LinkedList<>();

    public InMemoryAppender(Class<?> clazz) {
        ((Logger) LoggerFactory.getLogger(clazz)).addAppender(this);
        start();
    }

    public InMemoryAppender() {
        ((Logger) LoggerFactory.getLogger("root")).addAppender(this);
        start();
    }

    @Override
    protected void append(ILoggingEvent eventObject) {
        log.add(eventObject);
    }

    public boolean logContains(String message) {
        return log.stream().map(ILoggingEvent::getFormattedMessage).anyMatch(message::equals);
    }

    public int getLogSize() {
        return log.size();
    }
}
