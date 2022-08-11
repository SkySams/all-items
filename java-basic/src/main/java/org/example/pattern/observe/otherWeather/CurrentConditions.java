package org.example.pattern.observe.otherWeather;

/**
 * @author: zyh
 * @date: 2022/8/11
 */
public class CurrentConditions {

    private String temperature;
    private String humidity;
    private String pressure;

    private void display() {
        System.out.println("当前温度为:"+this.temperature);
        System.out.println("当前湿度为:"+this.humidity);
        System.out.println("当前大气压为:"+this.pressure);
    }

    /**
     *更新天气信息,更新完后将天气信息显示出来
     */
    public void update(String temperature,String  humidity,String pressure) {
        this.temperature=temperature;
        this.humidity=humidity;
        this.pressure=pressure;
        display();

    }

}
