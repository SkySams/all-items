package org.example.pattern.observe.weather;

/**
 * @author: zyh
 * @date: 2022/8/11
 */
public class AnyTimeWeather {

    /**
     * 发生气象报告
     * @param args
     */
    public static void main(String[] args) {
        WeatherSubscription subscription = new WeatherSubscription();
        subscription.registration(new People("Jack"));
        subscription.registration(new People("Luxi"));
        Weather weather = new Weather();
        weather.setMoisture(Double.valueOf(34.23));
        weather.setTemperature(Double.valueOf(34.23));
        subscription.sendAll(weather);
    }

}
