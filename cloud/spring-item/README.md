### seata自身的问题，当下有四种解决办法（任选一种即可）：

解决方法来源于：LocalDateTime转换异常，springboot版本：2.4.4 · Issue #3620 · seata/seata (github.com)

1.等seata官方修复并更新新版本
2.https://github.com/seata/seata/pull/3228/files 或者看这个pr的做法,通过spi,自定义你的jackson序列化器
3.查看你代码的实体类时间属性对应的数据库字段类型如果是datetime改成timestamp
4.修改seata的换序列化方式 配置中心中配置client.undo.logSerialization=kryo,client端再引入kryo的依赖包（ruoyi-cloud项目放到ruoyi-common-core/pom.xml下即可）