package org.example.pattern.proxy.one;

/**
 * @author: zyh
 * @date: 2022/10/12
 */
public class AppProxy {

    public static void main(String[] args) {
        WizardTower proxy = new WizardTowerProxy(new IvoryTower());
        proxy.enter(new Wizard("Red wizard"));
        proxy.enter(new Wizard("White wizard"));
        proxy.enter(new Wizard("Black wizard"));
        proxy.enter(new Wizard("Green wizard"));
        proxy.enter(new Wizard("Brown wizard"));
    }

}
