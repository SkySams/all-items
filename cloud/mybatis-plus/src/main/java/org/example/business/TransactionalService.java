package org.example.business;

/**
 * @author: zyh
 * @date: 2022/10/28
 */
public interface TransactionalService {

    void privateTrasational();

    void testPublice();


    void  optionUser();

    void testFinalTrasational();

    // 重写异常
    void overrideException();


    // 吞并异常
    void annexation();

}
