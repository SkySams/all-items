package org.example.aop.shoping;

import org.springframework.stereotype.Component;

/**
 * @author: zyh
 * @date: 2022/3/5
 */
@Component
public class Boy implements IBuy{
    @Override
    public String buy(double price) {
        System.out.println(String.format("男孩花了%s元买了一个游戏机", price));
        return "游戏机";
    }
}
