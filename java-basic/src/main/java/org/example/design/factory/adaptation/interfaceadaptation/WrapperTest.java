package org.example.design.factory.adaptation.interfaceadaptation;

/**
 * @author: zyh
 * @date: 2022/4/15
 */
public class WrapperTest {

    public static void main(String[] args) {
        Sourceable source1 = new SourceSub1();
        Sourceable source2 = new SourceSub2();

        source1.method1();
        source1.method2();
        source2.method1();
        source2.method2();
    }
}