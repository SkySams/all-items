# MYSQl 操作日记

1、添加索引
> ALTER TABLE tableName ADD index 索引名称(表字段)

1.1 添加唯一索引
> ALTER TABLE tableName ADD UNIQUE 索引名称(表字段)

1.2 查看表字段数据类型信息
> SHOW FULL COLUMNS FROM tableName

1.3 往表中添加新字段
> ALTER TABLE tableName ADD 字段名称 字段数据类型（VARCHER(150)）NOT NULL DEFAULT '' COMMENT "注释";

1.4 连表查询更新数据
> UPDATE tableName1 INSER JOIN tableName2 on tableName1.id = tableName2.id set tableName1.name = tableName2.name;

