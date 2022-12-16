package com.example.basicspring.service;

/**
 * @author: zyh
 * @date: 2022/12/16
 */
public interface PetStoreService {

    default void display() {
        System.out.println("default");
    };

}
