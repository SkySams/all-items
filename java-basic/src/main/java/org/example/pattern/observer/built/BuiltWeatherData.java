package org.example.pattern.observer.built;

/**
 * @author: zyh
 * @date: 2022/10/15
 */
public class BuiltWeatherData {

    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        RobotObserver robotObserver = new RobotObserver();
        weatherData.addObserver(robotObserver);
        weatherData.updateWeatherData(20,30,40);
    }

}
