package org.example.pattern.builder;

/**
 * @author: zyh
 * @date: 2022/12/28
 */

public enum Profession {

    WARRIOR, THIEF, MAGE, PRIEST;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
