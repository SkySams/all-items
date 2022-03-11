package org.example.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.order.entity.SmsAdvert;
import org.example.order.service.SmsAdvertService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 广告表(SmsAdvert)表控制层
 *
 * @author makejava
 * @since 2022-03-11 16:08:44
 */
@RestController
@RequestMapping("smsAdvert")
public class SmsAdvertController  {
    /**
     * 服务对象
     */
    @Resource
    private SmsAdvertService smsAdvertService;

    /**
     * 分页查询所有数据
     *
     * @param page      分页对象
     * @param smsAdvert 查询实体
     * @return 所有数据
     */
    @GetMapping
    public Object selectAll(Page<SmsAdvert> page, SmsAdvert smsAdvert) {
        return this.smsAdvertService.page(page, new QueryWrapper<>(smsAdvert));
    }

//    /**
//     * 通过主键查询单条数据
//     *
//     * @param id 主键
//     * @return 单条数据
//     */
//    @GetMapping("{id}")
//    public Object selectOne(@PathVariable Serializable id) {
//        return this.smsAdvertService.getById(id);
//    }
//
//    /**
//     * 新增数据
//     *
//     * @param smsAdvert 实体对象
//     * @return 新增结果
//     */
//    @PostMapping
//    public Object insert(@RequestBody SmsAdvert smsAdvert) {
//        return this.smsAdvertService.save(smsAdvert);
//    }
//
//    /**
//     * 修改数据
//     *
//     * @param smsAdvert 实体对象
//     * @return 修改结果
//     */
//    @PutMapping
//    public Object update(@RequestBody SmsAdvert smsAdvert) {
//        return this.smsAdvertService.updateById(smsAdvert);
//    }
//
//    /**
//     * 删除数据
//     *
//     * @param idList 主键结合
//     * @return 删除结果
//     */
//    @DeleteMapping
//    public Object delete(@RequestParam("idList") List<Long> idList) {
//        return this.smsAdvertService.removeByIds(idList);
//    }
}

