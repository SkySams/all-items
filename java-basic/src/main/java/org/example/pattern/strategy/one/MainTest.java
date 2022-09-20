package org.example.pattern.strategy.one;

/**
 * @author: zyh
 * @date: 2022/9/20
 */
public class MainTest {

    public static void main(String[] args) {
        Environment environment = new Environment(new AddStrategy());
        environment.print(10,23);

        environment.setCalculate(new SubstractStrategy());
        environment.print(100,23);
        System.out.println( environment.canEqual(environment));
    }


}
