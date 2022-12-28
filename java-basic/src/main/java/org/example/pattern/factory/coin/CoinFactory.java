package org.example.pattern.factory.coin;

/**
 * @author: zyh
 * @date: 2022/12/28
 */
public class CoinFactory {

    public static Coin getCoin(CoinType type) {
        return type.getConstructor().get();
    }

}
