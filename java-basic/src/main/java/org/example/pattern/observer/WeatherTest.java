package org.example.pattern.observer;

/**
 * @author: zyh
 * @date: 2022/10/14
 */
public class WeatherTest {

    public static void main(String[] args) {
        Weather weather = new Weather();
        weather.registerObserver(new CurrentWeather());

        weather.updateWeather(35,25,10);
        weather.updateWeather(0,0,0);

    }

}
