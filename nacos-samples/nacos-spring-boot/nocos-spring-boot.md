通过 Nacos Server 和 nacos-config-spring-boot-starter 实现配置的动态变更；
通过 Nacos Server 和 nacos-discovery-spring-boot-starter 实现服务的注册与发现。

[官方项目案例](https://github.com/nacos-group/nacos-examples)

注意：1.4.1版本开始该脚本默认是以集群方式运行，若需要单节点运行需要添加-m standalone参数运行脚本；
注意1.4.1之前的版本(不包括1.4.1)startup启动脚本默认是单机运行的，需要添加-m cluster