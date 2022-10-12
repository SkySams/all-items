package org.example.pattern.proxy.one;

import cn.hutool.log.StaticLog;

/**
 * @author: zyh
 * @date: 2022/10/12
 */
public class IvoryTower implements WizardTower{

    @Override
    public void enter(Wizard wizard) {
        StaticLog.info("{} enters the tower.", wizard);
    }
}
