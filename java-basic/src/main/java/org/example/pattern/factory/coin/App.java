package org.example.pattern.factory.coin;

/**
 * @author: zyh
 * @date: 2022/12/28
 */
public class App {


    public static void main(String[] args) {
        System.out.println("The alchemist begins his work.");
        CopperCoin coin1 = (CopperCoin) CoinFactory.getCoin(CoinType.COPPER);
        GoldCoin coin2 = (GoldCoin) CoinFactory.getCoin(CoinType.GOLD);

        System.out.println(coin1.description());
        System.out.println(coin2.description());

    }

}
