package org.example.util.enums;

/**
 * @author: zyh
 * @date: 2022/8/10
 */
public enum Person {

    TYPE_ONE(1,"中国");

    private int key;

    private String value;


    Person(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
