package org.example.pattern.observe.weather;

import lombok.Data;

/**
 * @author: zyh
 * @date: 2022/8/11
 */
public class Weather {

    private Double temperature;

    private Double moisture;

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getMoisture() {
        return moisture;
    }

    public void setMoisture(Double moisture) {
        this.moisture = moisture;
    }
}
