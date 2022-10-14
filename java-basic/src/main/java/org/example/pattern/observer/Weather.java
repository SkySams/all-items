package org.example.pattern.observer;

import java.util.HashSet;
import java.util.Set;

/**
 * 观察者模式第一版本
 * @author: zyh
 * @date: 2022/10/14
 */
public class Weather implements Subject{

    private Set<Observer> observers = new HashSet<>();

    // 温度
    private float temperature;

    // 湿度
    private float humidity;

    // 气压
    private float pressure;


    @Override
    public void registerObserver(Observer observer) {
        if (null != observer){
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        if (observers.contains(observer)){
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObsever() {
        observers.forEach(ob -> ob.update(temperature,humidity,pressure));
    }

    public void updateWeather (float temperature, float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.notifyObsever();
    }




}
