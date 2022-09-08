package org.example.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/8/22
 */
public interface IGoodsDao extends BaseMapper<Object> {

//    @MapKey("data")
    List<Map<String,Object>> purchaseGoogsQueryPage();

}
