package org.example.pattern.factory.coin;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

/**
 * @author: zyh
 * @date: 2022/12/28
 */
@RequiredArgsConstructor
@Getter
public enum CoinType {

    COPPER (CopperCoin::new),
    GOLD(GoldCoin::new);

    private final Supplier<Coin> constructor;

}
