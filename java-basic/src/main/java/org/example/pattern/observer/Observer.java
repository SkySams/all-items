package org.example.pattern.observer;

/**
 * 观察者
 */
public interface Observer {

    /**
     * 当气象台放生改变时，主题会把这些数据当成参数，传给观察者
     * @param temp
     * @param humidity
     * @param pressure
     */
    void update (float temp, float humidity, float pressure);

}
