package org.example.design.proxy;

import org.example.design.proxy.people.Client;
import org.example.design.proxy.shop.JackFlowerShop;
import org.junit.Test;

/**
 * @author: zyh
 * @date: 2022/5/24
 */
public class Me {

    /**
     * 购买
     */
    @Test
    public void buy_flower(){
        JackFlowerShop jackFlowerShop = new JackFlowerShop(new Client("lx"));
//        jackFlowerShop.buy_Rose();
    }

}
