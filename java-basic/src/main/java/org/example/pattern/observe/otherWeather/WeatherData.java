package org.example.pattern.observe.otherWeather;

/**
 * @author: zyh
 * @date: 2022/8/11
 */
public class WeatherData {

    private String temperature;

    private String humidity;

    private String pressure;

    private CurrentConditions current;

    public WeatherData(CurrentConditions current){
        this.current=current;
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

    public void setData(String temperature,String humidity,String pressure) {
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;
//设置信息时也要调用dataChange()方法。
        dataChange();
    }


    public void dataChange() {
        current.update(getTemperature(), getHumidity(), getPressure());
    }



}
