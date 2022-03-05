package org.example.aop.shoping;

import org.springframework.stereotype.Component;

/**
 * @author: zyh
 * @date: 2022/3/5
 */
@Component
public class Girl implements IBuy{
    @Override
    public String buy(double price) {
        System.out.println(String.format("女孩花了%s元买了一件漂亮的衣服", price));
        return "衣服";
    }
}
