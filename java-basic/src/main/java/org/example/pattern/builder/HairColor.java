package org.example.pattern.builder;

import java.util.Locale;

/**
 * @author: zyh
 * @date: 2022/12/28
 */
public enum HairColor {

    WHITE,
    BLOND,
    RED,
    BROWN,
    BLACK;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
