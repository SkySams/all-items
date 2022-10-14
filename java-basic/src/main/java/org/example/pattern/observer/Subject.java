package org.example.pattern.observer;

/**
 * 主题
 */
public interface Subject {

    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObsever(Observer observer);

}
