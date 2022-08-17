package org.example.pattern.observe.success;

/**
 * @author: zyh
 * @date: 2022/8/12
 */
public class CurrentConditions implements Observer {
    private String temperature;

    private String humidity;

    private String pressure;


    public void display() {
        System.out.println("当前温度为:" + this.temperature);
        System.out.println("当前湿度为:" + this.humidity);
        System.out.println("当前大气压为:" + this.pressure);
    }


    @Override
    public void update(String temperature, String humidity, String pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();

    }


}
