package org.example;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.NamingEvent;
import com.alibaba.nacos.api.naming.pojo.Cluster;
import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.api.naming.pojo.Service;
import com.alibaba.nacos.api.naming.pojo.healthcheck.AbstractHealthChecker;
import com.alibaba.nacos.api.naming.pojo.healthcheck.impl.Http;

import java.util.HashMap;
import java.util.Map;

/**
 * nacos 服务发现
 * @author: zyh
 * @date: 2022/10/15
 */
public class ServiceDiscovery {

    public static void main(String[] args) throws NacosException {
        NamingService naming = NamingFactory.createNamingService("127.0.0.1:8848");

        naming.registerInstance("myNacosServeri1", "11.8.8.8", 8999, "TEST");

//        Instance instance = new Instance();
//        instance.setIp("192.168.0.1");
//        instance.setPort(200);
//        instance.setHealthy(false);
//        instance.setWeight(2.0);
//        Map<String, String> instanceMeta = new HashMap<>();
//        instanceMeta.put("site", "et2");
//        instance.setMetadata(instanceMeta);
//
//        naming.registerInstance("nacos.test.10", instance);

        //注销实例
//        naming.deregisterInstance("myNacosServeri1", "11.8.8.8", 8999, "TEST");;

        // 获取实例
        System.out.println(naming.getAllInstances("myNacosServeri1"));

        // 获取健康或不健康实例列表
        System.out.println(naming.selectInstances("myNacosServeri1", true));

        //获取一个健康实例
//        System.out.println(naming.selectOneHealthyInstance("myNacosServeri1"));


        //监听服务
        naming.subscribe("myNacosServeri1", event -> {
            if (event instanceof NamingEvent) {
                System.out.println(((NamingEvent) event).getServiceName());
                System.out.println(((NamingEvent) event).getInstances());
            }
        });

        // 取消监听
        naming.unsubscribe("myNacosServeri1", event -> {});

//        while (true) {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

    }

}
