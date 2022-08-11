package org.example.pattern.observe.weather;

import java.util.HashSet;
import java.util.Set;

/**
 * 气象订阅
 * @author: zyh
 * @date: 2022/8/11
 */
public class WeatherSubscription {

     Set<ThirdPart> set = new HashSet<>();

    // 注册
    public  void registration (ThirdPart thirdPart){
        set.add(thirdPart);
    }

    // 取消
    public void cancel(ThirdPart thirdPart){
        if (set.contains(thirdPart)){
            set.remove(thirdPart);
        }
    }

    // 单个发送
    public void send(ThirdPart thirdPart, Double temperature, Double moisture){
        System.out.println(thirdPart.getName()+""+temperature+""+moisture);
    }

    // 全部发送
    public void sendAll(Weather weather){
        for (ThirdPart thirdPart : set){
            System.out.println(thirdPart.getName()+"温度："+weather.getTemperature()+"湿度："+weather.getMoisture());
        }
    }


    public  Integer getSize(){
        return set.size();
    }

}
