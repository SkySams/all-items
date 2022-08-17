package org.example.pattern.observe.success;

/**
 * @author: zyh
 * @date: 2022/8/12
 */
public interface Observer {

    void update(String temperature, String humidity, String pressure);

}
