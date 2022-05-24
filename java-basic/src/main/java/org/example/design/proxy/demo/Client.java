package org.example.design.proxy.demo;

/**
 * @author: zyh
 * @date: 2022/4/16
 */
public class Client {

    public static void main(String[] args) {
        BeautifulGirl mm = new BeautifulGirl("lvsi");

        HerChum chum = new HerChum(mm);
        chum.giveBook();
        chum.giveChocolate();
        chum.giveFlowers();
    }
}
