package com.example.basicspring.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 配置文件读取
 * @author: zyh
 * @date: 2023/2/3
 */
public class PropertiesUtil {

    private static Logger log = LoggerFactory.getLogger(PropertiesUtil.class);
    private static Properties props;
    static String fileName = "i18n\\mess_en_US.properties";
    //    项目根目录文件夹内读取
//     static {
//         if (props == null) {
//             props = new Properties();
//             try {
//                 props.load(new FileInputStream(fileName));
//             } catch (IOException e) {
//                 log.error("配置文件读取异常", e);
//             }
//         }
//     }

    /**
     * resource文件夹内读取
     */
    static {
        props = new Properties();
        try {
            InputStream inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
            props.load(new InputStreamReader(inputStream, "UTF-8"));
        } catch (IOException e) {
            log.error("配置文件读取异常", e);
        }
    }

    /**
     * 根据配置文件中的key获取value
     * @param key
     * @return
     */
    public static String getProperty(String key) {
        String value = props.getProperty(key.trim());
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return value.trim();
    }

    /**
     * 根据配置文件中的key获取value (当获取不到值赋予默认值)
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getProperty(String key, String defaultValue) {
        String value = props.getProperty(key.trim());
        if (StringUtils.isEmpty(value)) {
            value = defaultValue;
        }
        return value.trim();
    }


    public static void main(String[] args) {
        System.out.println("配置文件中有："+PropertiesUtil.getProperty("请求失败"));
        System.out.println("配置文件无有，赋予默认值"+PropertiesUtil.getProperty("请求失败","success"));
    }
}