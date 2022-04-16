package org.example.design.proxy.demo;

/**
 * @author: zyh
 * @date: 2022/4/16
 */
public class You implements GiveGift {

    private BeautifulGirl mm;

    public You(BeautifulGirl girl) {
        this.mm = girl;
    }

    @Override
    public void giveBook() {
        System.out.println(mm.getName() + ",送你一本书....");
    }

    @Override
    public void giveChocolate() {
        System.out.println(mm.getName() + ",送你一盒巧克力....");
    }

    @Override
    public void giveFlowers() {
        System.out.println(mm.getName() + ",送你一束花....");
    }

}
