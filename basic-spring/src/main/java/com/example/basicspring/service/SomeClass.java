package com.example.basicspring.service;

import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/12/19
 */
public class SomeClass {

    private Map<String, Float> accounts;

    public void setAccounts(Map<String, Float> accounts) {
        this.accounts = accounts;
    }

    public Map<String, Float> getAccounts() {
        return accounts;
    }
}

