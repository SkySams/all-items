package org.example.pattern.decorator;

public class StarbuzzCoffee {


    /**
     * 下单测试
     * @param args
     */
    public static void main(String[] args) {

        // 不需要任何调料的咖啡
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription()+ "， $ " + beverage.cost());


        Beverage beverage1 = new HouseBlend();
        beverage1 = new Mocha(beverage1);
        System.out.println(beverage1.getDescription());
        System.out.println(beverage1.cost());

    }

}
