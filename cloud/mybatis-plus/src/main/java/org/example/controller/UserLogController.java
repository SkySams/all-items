package org.example.controller;

import org.example.entity.UserLog;
import org.example.service.UserLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (UserLog)表控制层
 *
 * @author makejava
 * @since 2022-10-28 09:42:54
 */
@RestController
@RequestMapping("userLog")
public class UserLogController {
    /**
     * 服务对象
     */
    @Resource
    private UserLogService userLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public UserLog selectOne(Integer id) {
        return this.userLogService.queryById(id);
    }

}