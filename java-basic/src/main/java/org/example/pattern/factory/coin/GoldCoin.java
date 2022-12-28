package org.example.pattern.factory.coin;

/**
 * @author: zyh
 * @date: 2022/12/28
 */
public class GoldCoin implements Coin{

    static final String DESCRIPTION = "This is a gold coin.";

    @Override
    public String description() {
        return DESCRIPTION;
    }
}
