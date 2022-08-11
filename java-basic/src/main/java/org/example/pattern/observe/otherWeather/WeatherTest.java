package org.example.pattern.observe.otherWeather;

/**
 * @author: zyh
 * @date: 2022/8/11
 */
public class WeatherTest {

    public static void main(String[] args) {
        CurrentConditions currentConditions = new CurrentConditions();
        WeatherData weatherData = new WeatherData(currentConditions);
        weatherData.setData("1", "2", "3");
        weatherData.setData("4", "5", "6");
    }

}
