package org.example.pattern.strategy.one;

/**
 * @author: zyh
 * @date: 2022/9/20
 */
public class AddStrategy implements Calculate {
    @Override
    public int calc(int x, int y) {
        return (x + y);
    }
}
