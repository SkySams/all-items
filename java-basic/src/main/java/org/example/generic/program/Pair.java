package org.example.generic.program;

import lombok.Data;

/**
 * 简单泛型
 * @param <T>
 */
@Data
public class Pair<T> {

    private T first;
    private T second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }


}
