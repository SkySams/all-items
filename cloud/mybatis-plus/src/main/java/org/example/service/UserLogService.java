package org.example.service;

import org.example.entity.UserLog;
import java.util.List;

/**
 * (UserLog)表服务接口
 *
 * @author makejava
 * @since 2022-10-28 09:42:54
 */
public interface UserLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserLog queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<UserLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param userLog 实例对象
     * @return 实例对象
     */
    UserLog insert(UserLog userLog);

    /**
     * 修改数据
     *
     * @param userLog 实例对象
     * @return 实例对象
     */
    UserLog update(UserLog userLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}