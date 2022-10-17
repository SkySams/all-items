package org.example.pattern.observer.built;

import org.example.pattern.observer.DisplayElement;

import java.util.Observable;
import java.util.Observer;

/**
 * @author: zyh
 * @date: 2022/10/15
 */
public class RobotObserver implements Observer, DisplayElement {

    /**
     * 保存气象台的数据，发布到现实版上
     */
    // 温度、湿度、气压
    private float temperature;

    private float humidity;

    private float pressure;

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Object: "+ arg);
        WeatherData weatherData = (WeatherData) o;
        this.humidity = weatherData.getHumidity();
        this.temperature = weatherData.getTemperature();
        this.pressure = weatherData.getPressure();
        this.display();
    }

    @Override
    public void display() {
        System.out.println(humidity);
        System.out.println(temperature);
        System.out.println(pressure);
    }
}
