package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.example.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author: zyh
 * @date: 2022/7/23
 */
@Repository
public interface UserDao extends BaseMapper<User> {
}
