package com.example.basic.repository;

import com.example.basic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: zyh
 * @date: 2022/4/7
 */
@Repository
public interface UserRepository  extends JpaRepository<User,Long> {

    User findOneByLogin(String login);

}
