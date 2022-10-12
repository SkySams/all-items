package org.example.pattern.proxy.one;

import lombok.Data;

/**
 * @author: zyh
 * @date: 2022/10/12
 */
@Data
public class Wizard {

    private final String name;

    public Wizard(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
