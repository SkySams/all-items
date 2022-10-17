package org.example;

import com.alibaba.nacos.api.annotation.NacosInjected;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import org.junit.jupiter.api.Test;

/**
 * @author: zyh
 * @date: 2022/10/15
 */
public class TestNacos {

    @NacosInjected
    private ConfigService configService;

    @NacosInjected
    private NamingService namingService;

    @Test
    public void testPublishConfig() throws NacosException {
        configService.publishConfig("nacosspring", "DEFAULT_GROUP", "9527");
        System.out.println("long ");
    }

}
