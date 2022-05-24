package org.example.design.proxy.people;

/**
 * @author: zyh
 * @date: 2022/5/24
 */
public class Client implements People{
    private String name;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
