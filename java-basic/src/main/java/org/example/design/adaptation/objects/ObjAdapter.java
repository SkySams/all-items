package org.example.design.adaptation.objects;

import org.example.design.adaptation.Target;

/**
 * @author: zyh
 * @date: 2022/6/6
 */
public class ObjAdapter implements Target {

    private ObjAdatee objAdatee;

    public ObjAdapter(ObjAdatee objAdatee) {
        this.objAdatee = objAdatee;
    }

    @Override
    public void request() {
        this.objAdatee.superRequest();
    }
}
