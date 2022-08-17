package org.example.pattern.observe.success;

/**
 * 主题
 * @author: zyh
 * @date: 2022/8/12
 */
public interface Subject {

    void registerObserver(Observer subject);

    void removeObserver(Observer subject);

    void notifyAllObserver();

}
