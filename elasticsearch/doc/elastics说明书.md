
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