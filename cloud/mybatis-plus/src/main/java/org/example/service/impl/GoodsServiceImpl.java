package org.example.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.dao.IGoodsDao;
import org.example.service.GoodsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/8/22
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<IGoodsDao,Object> implements GoodsService {

    @Override
    public List<Map<String,Object>> de() {
        return this.baseMapper.purchaseGoogsQueryPage();
    }
}
