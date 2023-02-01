package com.example.basicspring.service;

import com.example.basicspring.entity.MUser;

/**
 * @author: zyh
 * @date: 2023/1/19
 */
public interface UserService {

    MUser addUser(Integer id);

    void testAsync();

}
