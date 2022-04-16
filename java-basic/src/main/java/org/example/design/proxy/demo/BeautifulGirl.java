package org.example.design.proxy.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: zyh
 * @date: 2022/4/16

 */
public class BeautifulGirl {
    private String name;

    public BeautifulGirl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
