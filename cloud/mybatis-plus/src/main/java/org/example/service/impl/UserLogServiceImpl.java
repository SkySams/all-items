package org.example.service.impl;

import lombok.Data;
import org.example.entity.UserLog;
import org.example.dao.UserLogDao;
import org.example.service.UserLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserLog)表服务实现类
 *
 * @author makejava
 * @since 2022-10-28 09:42:54
 */
@Service("userLogService")
public class UserLogServiceImpl implements UserLogService {
    @Resource
    private UserLogDao userLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserLog queryById(Integer id) {
        return this.userLogDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<UserLog> queryAllByLimit(int offset, int limit) {
        return this.userLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param userLog 实例对象
     * @return 实例对象
     */
    @Override
    public UserLog insert(UserLog userLog) {
        this.userLogDao.insert(userLog);
        return userLog;
    }

    /**
     * 修改数据
     *
     * @param userLog 实例对象
     * @return 实例对象
     */
    @Override
    public UserLog update(UserLog userLog) {
        this.userLogDao.update(userLog);
        return this.queryById(userLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.userLogDao.deleteById(id) > 0;
    }
}