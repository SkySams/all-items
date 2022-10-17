package org.example;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author: zyh
 * @date: 2022/10/15
 */
public class TestNacosClient {


    /**
     * JACA JDK
     * @param args
     */
    public static void main(String[] args) {
        try {
            String serverAddr = "127.0.0.1:8848";
            String dataId = "nacos";
            String group = "DEFAULT_GROUP";
            Properties properties = new Properties();
            properties.put("serverAddr", serverAddr);
            ConfigService configService = NacosFactory.createConfigService(properties);
            String content = configService.getConfig(dataId, group, 5000);
            System.out.println(content);

            /**
             * 监听器
             */
            Listener nacosListener = new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String s) {
                    System.out.println("recieve1:" + s);
                }
            };
            configService.addListener(dataId,group,nacosListener);

            /**
             * 删除监听器
             */
//            configService.removeListener(dataId,group,nacosListener);


            /**
             * 发布配置
             */
            boolean isPublishOk = configService.publishConfig(dataId, group, "content");
            System.out.println(isPublishOk);

            /**
             * 删除配置
             */
            boolean isRemoveOk = configService.removeConfig(dataId,group);
            System.out.println(isRemoveOk);

            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (NacosException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
