package org.example.service;

/**
 * @author: zyh
 * @date: 2022/10/28
 */
public class ClientService {

    private static ClientService clientService = new ClientService();
    private ClientService() {}

    public static ClientService createInstance() {
        return clientService;
    }

}
