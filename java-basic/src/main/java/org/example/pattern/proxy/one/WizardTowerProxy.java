package org.example.pattern.proxy.one;

import cn.hutool.log.StaticLog;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: zyh
 * @date: 2022/10/12
 */
public class WizardTowerProxy implements WizardTower {

    private static final int NUM_WIZARDS_ALLOWED = 3;

    private int numWizards;

    private final WizardTower tower;

    public WizardTowerProxy(WizardTower tower) {
        this.tower = tower;
    }

    @Override
    public void enter(Wizard wizard) {
        if (numWizards < NUM_WIZARDS_ALLOWED) {
            tower.enter(wizard);
            numWizards++;
        } else {
            StaticLog.info("{} is not allowed to enter!", wizard);
        }
    }
}