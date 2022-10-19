package org.example.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @@author: lixl
 * @date: 2019-08-09 18:21 @description: mybatis-plus代码生成器(用于生成entity)
 */
public class MyBatisPlusGenerator {
    /**
     * 根路径
     */
    private static String path = "E:\\Temp";
    /**
     * table名字
     */
    private static String[] tables = new String[] { "customer_cancel_record" };

    /**
     * 模块名称
     */
    private static final String MODULE_NAME = "customer";

    /**
     * 名称前缀
     */
    private static final String PREFIX = "";

    /**
     * 自动填充字段
     */
    private static final List<String> AUTO_FILL_FIELD = new ArrayList<>(16);

    static {
        AUTO_FILL_FIELD.addAll(Arrays.asList("updateTime", "createTime", "lastUpdateTime", "deleted"));
    }

    public static void main(String[] args) {
        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        // 是否支持AR模式
        config.setActiveRecord(false)
                // 作者
                .setAuthor("cwhisky")
                // 生成路径
                .setOutputDir(path + "/src/main/java")
                // 文件覆盖
                .setFileOverride(true)
                // 主键策略
                .setIdType(IdType.AUTO)
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setServiceName("I%sService").setServiceImplName("%sServiceImpl")
                .setControllerName("%sController").setMapperName("I%sMapper").setXmlName("%sMapper")
                // 生成文件后 打开文件夹
                .setOpen(true)
                // XML ResultMap
                .setBaseResultMap(true)
                // XML columList
                .setBaseColumnList(true);

        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        // 设置数据库类型
        dsConfig.setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://192.168.0.161:3306/database?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false")
                .setUsername("root").setPassword("123456")
                .setTypeConvert(new MySqlTypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if (fieldType.toLowerCase().contains("tinyint")) {
                    return DbColumnType.BOOLEAN;
                }
                //将数据库中datetime转换成date
                if (fieldType.toLowerCase().contains("datetime")) {
                    return DbColumnType.DATE;
                }
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });

        //3. 策略配置
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setColumnNaming(NamingStrategy.underline_to_camel)
                // 数据库表映射到实体的命名策略
                .setNaming(NamingStrategy.underline_to_camel)
                // 使用Lombok
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .entityTableFieldAnnotationEnable(true)
                .setLogicDeleteFieldName("deleted")
                // 生成的表
                .setInclude(tables);

        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent("com.cwhisky."+ MODULE_NAME).setService("service").setMapper("mapper")
                .setServiceImpl("service.impl").setController("controller").setEntity("eo");
        //5.自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // mapper自定义输出文件名
                return path + "/src/main/resources/mapper/" + PREFIX + tableInfo.getEntityName() + "Mapper"
                        + StringPool.DOT_XML;
            }
        });
        // 如果模板引擎是 velocity
        templatePath = "/templates/business.java.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // mapper自定义输出文件名
                return path + "/src/main/java/com/cwhisky/" + MODULE_NAME + "/business/I" + PREFIX
                        + tableInfo.getEntityName()
                        + "Business"
                        + StringPool.DOT_JAVA;
            }
        });
        // 如果模板引擎是 velocity
        templatePath = "/templates/businessImpl.java.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // mapper自定义输出文件名
                return path + "/src/main/java/com/cwhisky/" + MODULE_NAME + "/business/impl/"
                        + PREFIX + tableInfo.getEntityName() + "BusinessImpl"
                        + StringPool.DOT_JAVA;
            }
        });
        // 如果模板引擎是 velocity
        templatePath = "/templates/entity.java.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                for (TableField field : tableInfo.getFields()) {
                    if (AUTO_FILL_FIELD.contains(field.getPropertyName())) {
                        if (field.getPropertyName().equals("createTime")) {
                            field.setFill("INSERT");
                        } else {
                            field.setFill("INSERT_UPDATE");
                        }
                    }
                }
                // mapper自定义输出文件名
                return path + "/src/main/java/com/cwhisky/" + MODULE_NAME + "/eo/T" + PREFIX + tableInfo.getEntityName()
                        + "EO"
                        + StringPool.DOT_JAVA;
            }
        });
        // 如果模板引擎是 velocity
        templatePath = "/templates/bo.java.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // mapper自定义输出文件名
                return path + "/src/main/java/com/cwhisky/" + MODULE_NAME + "/bo/" + PREFIX + tableInfo.getEntityName()
                        + "BO"
                        + StringPool.DOT_JAVA;
            }
        });
        // 如果模板引擎是 velocity
        templatePath = "/templates/vo.java.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // mapper自定义输出文件名
                return path + "/src/main/java/com/cwhisky/" + MODULE_NAME + "/vo/" + PREFIX + tableInfo.getEntityName()
                        + "VO"
                        + StringPool.DOT_JAVA;
            }
        });
        // 如果模板引擎是 velocity
        templatePath = "/templates/dto.java.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // mapper自定义输出文件名
                return path + "/src/main/java/com/cwhisky/" + MODULE_NAME + "/dto/" + PREFIX + tableInfo.getEntityName()
                        + "DTO"
                        + StringPool.DOT_JAVA;
            }
        });
        // 如果模板引擎是 velocity
        templatePath = "/templates/model.java.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // mapper自定义输出文件名
                return path + "/src/main/java/com/cwhisky/" + MODULE_NAME + "/model/" + PREFIX
                        + tableInfo.getEntityName()
                        + "Model"
                        + StringPool.DOT_JAVA;
            }
        });
        // 如果模板引擎是 velocity
        templatePath = "/templates/domain.java.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // mapper自定义输出文件名
                return path + "/src/main/java/com/cwhisky/" + MODULE_NAME + "/domain/" + PREFIX
                        + tableInfo.getEntityName()
                        + "Domain"
                        + StringPool.DOT_JAVA;
            }
        });
        // 如果模板引擎是 velocity
        templatePath = "/templates/query.java.vm";
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // mapper自定义输出文件名
                return path + "/src/main/java/com/cwhisky/" + MODULE_NAME + "/query/" + PREFIX
                        + tableInfo.getEntityName()
                        + "Query"
                        + StringPool.DOT_JAVA;
            }
        });
        // ------------form vo 使用默认模板请注释-end-----------
        cfg.setFileOutConfigList(focList);

        // 6 配置模板 自定义模板/在resources/templates 可以编辑
        TemplateConfig templateConfig = new TemplateConfig();
        // 关闭默认 xml 生成，调整生成 至 根目录
        templateConfig.setEntity("/templates/entity.java").setService("/templates/service.java")
                .setController("/templates/controller.java").setMapper("/templates/mapper.java")
                .setServiceImpl("/templates/serviceImpl.java").setXml(null);

        //7. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config).setDataSource(dsConfig).setStrategy(stConfig).setCfg(cfg)
                .setPackageInfo(pkConfig).setTemplate(templateConfig);

        //8. 执行
        ag.execute();
        delFile(new File(path + "/src/main/java/com/cwhisky/" + MODULE_NAME + "/eo"));
    }

    private static void delFile(File file) {
        if (!file.exists()) {
            return;
        }
        for (File a : file.listFiles()) {
            if (!a.getName().startsWith("T")) {
                if (a.isDirectory()) {
                    delFile(a);
                }
                if (a.isFile() || a.listFiles().length <= 0) {
                    a.delete();
                }
            }
        }
    }

}