package org.example.pattern.observer;

import org.example.pattern.observer.DisplayElement;
import org.example.pattern.observer.Observer;

/**
 * @author: zyh
 * @date: 2022/10/14
 */
public class CurrentWeather implements Observer, DisplayElement {

    // 温度
    private float temperature;

    // 湿度
    private float humidity;

    // 气压
    private float pressure;

    /**
     * 当气象台放生改变时，主题会把这些数据当成参数，传给观察者
     *
     * @param temperature
     * @param humidity
     * @param pressure
     */
    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperature = temperature;
        this.display();
    }

    @Override
    public void display() {
        System.out.println("温度："+temperature);
        System.out.println("湿度："+humidity);
        System.out.println("气压："+pressure);
    }
}
