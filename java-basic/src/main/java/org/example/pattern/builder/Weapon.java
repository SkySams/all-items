package org.example.pattern.builder;

/**
 * @author: zyh
 * @date: 2022/12/28
 */
public enum Weapon {

    DAGGER, SWORD, AXE, WARHAMMER, BOW;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
