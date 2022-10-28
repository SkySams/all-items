package org.example.service;


import org.example.service.impl.UserLogServiceImpl;

/**
 * @author: zyh
 * @date: 2022/10/28
 */
public class DefaultServiceLocator {

    private static UserLogService  clientService = new UserLogServiceImpl();

    public UserLogService createClientServiceInstance() {
        return clientService;
    }

}
