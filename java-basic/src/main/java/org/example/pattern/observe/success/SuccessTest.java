package org.example.pattern.observe.success;

/**
 * @author: zyh
 * @date: 2022/8/12
 */
public class SuccessTest {

    public static void main(String[] args) {
        WeatherData data=new WeatherData();
        data.registerObserver(new CurrentConditions());
        data.setData("1", "2", "3");
        data.registerObserver(new Baidu());
        data.setData("3", "4", "5");
    }
}
