package org.example.pattern.factory.coin;

/**
 * @author: zyh
 * @date: 2022/12/28
 */
public class CopperCoin implements Coin{

    static final String DESCRIPTION = "This is a copper coin.";

    @Override
    public String description() {
        return DESCRIPTION;
    }
}
