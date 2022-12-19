package com.example.basicspring.service;

/**
 * @author: zyh
 * @date: 2022/12/16
 */
public class ClientService {

    private static ClientService clientService = new ClientService();
    private ClientService() {}

    public static ClientService createInstance() {
        return clientService;
    }

}
