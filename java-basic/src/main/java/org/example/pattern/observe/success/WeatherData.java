package org.example.pattern.observe.success;

import java.util.ArrayList;
import java.util.List;

/**
 * 核心信息，管理观察者
 * @author: zyh
 * @date: 2022/8/12
 */
public class WeatherData implements Subject{

    private String temperature;
    private String humidity;
    private String pressure;

    private List<Observer> observerList;

    public void setData(String temperature,String humidity,String pressure){
        this.temperature = temperature;
        this.humidity=humidity;
        this.pressure=pressure;
        dataChange();
    }

    public void dataChange() {
//        current.update(getTemperature(), getHumidity(), getPressure());
    }


    @Override
    public void registerObserver(Observer subject) {
        observerList.add(subject);
    }

    @Override
    public void removeObserver(Observer subject) {
        observerList.remove(subject);
    }

    @Override
    public void notifyAllObserver() {
        for (Observer subject : observerList){
            subject.update(getTemperature(),getHumidity(),getPressure());
        }
    }


    public WeatherData() {
        observerList = new ArrayList<>();
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }
}
