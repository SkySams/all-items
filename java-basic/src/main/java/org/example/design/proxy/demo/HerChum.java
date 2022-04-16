package org.example.design.proxy.demo;

/**
 * @author: zyh
 * @date: 2022/4/16
 */
public class HerChum implements GiveGift{

    You you;

    public HerChum(BeautifulGirl mm){
        you = new You(mm);
    }

    @Override
    public void giveBook() {
        you.giveBook();
    }

    @Override
    public void giveChocolate() {
        you.giveChocolate();
    }
    @Override
    public void giveFlowers() {
        you.giveFlowers();
    }
}
