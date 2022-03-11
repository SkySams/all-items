package org.example.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.order.dao.SmsAdvertDao;
import org.example.order.entity.SmsAdvert;
import org.example.order.service.SmsAdvertService;
import org.springframework.stereotype.Service;

/**
 * 广告表(SmsAdvert)表服务实现类
 *
 * @author makejava
 * @since 2022-03-11 16:08:45
 */
@Service("smsAdvertService")
public class SmsAdvertServiceImpl extends ServiceImpl<SmsAdvertDao, SmsAdvert> implements SmsAdvertService {

}

