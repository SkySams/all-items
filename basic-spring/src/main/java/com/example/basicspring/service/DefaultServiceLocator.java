package com.example.basicspring.service;

/**
 * @author: zyh
 * @date: 2022/12/19
 */
public class DefaultServiceLocator {

    private static ClientService clientService = new ClientService();

    public ClientService createClientServiceInstance() {
        return clientService;
    }

}

