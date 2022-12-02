
kibana 新增账号
>kibana-keystore create
kibana-keystore add elasticsearch.username elastic
kibana-keystore add elasticsearch.password elastic.123

删除账号
>kibana-keystore remove elasticsearch.username
kibana-keystore remove elasticsearch.password


elastic 设置账号密码
>elasticsearch-setup-passwords interactive
>

查看keystore列表：
>kibana-keystore list

证书过期：
>降低基础版本： curl -XPOST http://127.0.0.1:9200/_license/start_basic?acknowledge=true

许可证：
>http://127.0.0.1:9200/_license

中文文档：
>https://learnku.com/docs/elasticsearch73/7.3