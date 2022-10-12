package org.example.pattern.duck;

import org.example.pattern.duck.action.impl.DryDuckAction;
import org.example.pattern.duck.pettern.DryDuck;

/**
 * @author: zyh
 * @date: 2022/9/30
 */
public class DockWindows {

    public static void main(String[] args) {
        DryDuck dryDuck = new DryDuck(new DryDuckAction());
        dryDuck.color();
        dryDuck.swim();
        dryDuck.play();
    }

}
