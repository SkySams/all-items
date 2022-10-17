package org.example.pattern.observer.built;

import lombok.Data;

import java.util.Observable;

/**
 * @author: zyh
 * @date: 2022/10/15
 */
@Data
public class WeatherData extends Observable {

    // 温度、湿度、气压
    private float temperature;

    private float humidity;

    private float pressure;


    public void updateWeatherData(float temperature,float humidity,float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.setChanged();
        this.notifyObservers("由于受到强降温的影响; 气候发生改变");
    }

}
