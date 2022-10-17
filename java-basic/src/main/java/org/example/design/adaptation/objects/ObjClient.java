package org.example.design.adaptation.objects;

/**
 * @author: zyh
 * @date: 2022/6/6
 */
public class ObjClient {

    public static void main(String[] args) {
        // 对象适配器
        ObjAdapter objAdapter = new ObjAdapter(new ObjAdatee());
        objAdapter.request();
    }
}
