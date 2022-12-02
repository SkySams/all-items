GET {{url}}/_cat/indices?v
获取所有索引 health status index uuid pri rep docs.count docs.deleted store.size pri.store.size
> health: 当前服务器健康状态：green(集群)、yellow(单点正常，集群不完整) red (单点不正常)
> status: 索引打卡、关闭状态
> index：索引名
> uuid: 索引唯一编号
> pri: 主分片数量
> rep: 副本数量
> docs.count: 可用文档数量
> docs.deleted: 文档删除状态（逻辑删除）
> store.size: 主分片和副分片整体空间大小
> pri.store.size: 主分片占空间大小


索引操作: PUT {{url}}/{{index}}

相应示例
> acknowledged: true 操作成功
> shards_acknowledged：true 分片操作成功
> index（索引名称）： shopping


查看单个索引： GET {{url}}/{{index}}

响应示例
>{
    "indexName"【索引名称】: {
        "aliases"【别名】:{},
        "mappings"【映射】:{},
        "settings"【设置】:{
            "index"【设置-索引】:{
                "create_date"【设置-索引-创建时间】:"1614265373911",
                "number_of_shards"【设置-索引-主频数量】: "1",
                "number_of_replicas"【设置-索引-副片数量】: "1",
                "uuid"【设置-索引-唯一标识】: "30xUXIfGQSy80WO3zTiY1w",
                "provided_name"【设置-索引-名称】: "indexName"
                "version"【设置-索引-版本】:{
                    "create": "7080099"
                }
                
            }
        }
    }
}    

创建文档: POST {{url}}/{{index}}/{{type}}

请求参数
>{
    "title":"小米手机",
    "category":"小米",
    "images":"http://www.gulixueyuan.com/xm.jpg",
    "price":3999.00
}


响应示例
>{
    "_index"【索引】: "shopping",
    "_type"【类型-文档】: "_doc",
    "_id"【唯一标识】: "S9eU0IQB_wQnKC4b8iNW", #可以类比为 MySQL 中的主键，随机生成
    "_version"【本版】: 1,
    "result"【结果】: "created", #create表示创建成功, update 表示更改成功
    "_shards"【分片】: {
        "total"【分片-总数】: 2,
        "successful"【分片-成功】: 1,
        "failed"【分片-失败】: 0
    },
    "_seq_no": 2,
    "_primary_term": 1
}


条件删除文档： POST {{url}}/{{index}}/_delete_by_query
请求参数
>{
    "query":{
        "match"【条件】:{
        "price":4000.00
        }
    }
}

响应示例
>{
    "took"【耗时】: 175,
    "timed_out"【是否超时】: false,
    "total"【总数】: 2,
    "deleted"【删除数量】: 2,
    "batches": 1,
    "version_conflicts": 0,
    "noops": 0,
    "retries": {
    "bulk": 0,
    "search": 0
    },
    "throttled_millis": 0,
    "requests_per_second"【每秒请求】: -1.0,
    "throttled_until_millis": 0,
    "failures": []
}


创建映射：PUT {{url}}/{{index}}/_mapping （创建映射之前，需要先创建索引）

请求参数
>{
    "properties": {
        "name":{
            "type": "text",
            "index": true
        },
        "sex":{
            "type": "text",
            "index": false
        },
        "age":{
            "type": "long",
            "index": false
        }
    }
}

映射数据说明：
> 字段名
> type：类型，Elasticsearch 中支持的数据类型非常丰富
>   String类型： text—可分词、keyword-不可分词
>Numerical：数值类型，分两类
>   基本数据类型：long、integer、short、byte、double、float、half_float
>   浮点数的高精度类型：scaled_float
> Date：日期类型
> Array：数组类型
> Object：对象
> index：是否索引，默认为 true，也就是说你不进行任何配置，所有字段都会被索引。
> true：字段会被索引，则可以用来进行搜索
> false：字段不会被索引，不能用来搜索
> store：是否将数据进行独立存储，默认为 false
> 原始的文本会存储在_source 里面，默认情况下其他提取出来的字段都不是独立存储的，是从_source 里面提取出来的。
> 当然你也可以独立的存储某个字段，只要设置"store": true 即可，
> 获取独立存储的字段要比从_source 中解析快得多，但是也会占用更多的空间，所以要根据实际业务需求来设置。
> analyzer：分词器，这里的 ik_max_word 即使用 ik 分词器。

查看映射: GET {{url}}/{{index}}/_mapping



查询所有文档：GET {{url}}/{{index}}/_search
请求参数
>{
    "query": {
        "match_all": {}
    }
}
> query：这里的 query 代表一个查询对象，里面可以有不同的查询属性
> match_all：查询类型，例如：match_all(代表查询所有)， match，term ， range 等 {查询条件}：查询条件会根据类型的不同，写法也有差异


响应实例
>{
    "took"【查询花费时间，单位毫秒】: 0,
    "timed_out"【是否超时】: false,
    "_shards"【分片信息】: {
        "total"【总数】: 1,
        "successful"【成功】: 1,
        "skipped"【忽略】: 0,
        "failed"【失败】: 0
    },
    "hits"【搜索命中结果】: {
        "total"【搜索条件匹配的文档总数】: {
        "value"【总命中计数的值】: 11,
        "relation"【计数规则】: "eq" # eq 表示计数准确， gte 表示计数不准确
        },
        "max_score"【匹配度分值】: 1.0,
         "hits"【命中结果集合】: []
    }
}

匹配查询: GET {{url}}/{{index}}/_search
请求参数
>{
    "query": {
        "match": {
            "name": "Mueller"
        }
    }
}

 match : 匹配类型查询，会把查询条件进行分词，然后进行查询，多个词条之间是 or 的关系

字段匹配查询：GET {{url}}/{{index}}/_search
请求参数
>{
    "query": {
        "multi_match": {
            "query": "Mueller",
            "fields": ["name","nickname"]
        }
    }
}

multi_match 与 match 类似，不同的是它可以在多个字段中查询。


关键字精确查询: GET {{url}}/{{index}}/_search
请求参数
>{
    "query": {
        "term": {
        "name": {
            "value": "Bartoletti"
          }
        }
    }
}


term 查询，精确的关键词匹配查询，不对查询条件进行分词

多关键字精确查询: GET {{url}}/{{index}}/_search
请求参数
>{
    "query": {
        "terms": {
            "name": ["zhangsan","lisi"]
        }
    }
}


指定查询字段: GET {{url}}/{{index}}/_search
请求参数
>{
    "_source": ["name","nickname"],
    "query": {
        "terms": {
            "nickname": ["zhangsan"]
        }
    }
}

默认情况下，Elasticsearch 在搜索的结果中，会把文档中保存在_source 的所有字段都返回。
如果我们只想获取其中的部分字段，我们可以添加_source 的过滤


过滤字段: GET {{url}}/{{index}}/_search
请求参数
>{
　 "_source": {
　 "includes": ["name","nickname"]
　 },
　 "query": {
　　 "terms": {
　　　 "nickname": ["zhangsan"]
　　 }
　 }
}
>{
　 "_source": {
　　 "excludes": ["name","nickname"]
　 },
　 "query": {
　　 "terms": {
　　　 "nickname": ["zhangsan"]
　　 }
　 }
}

includes：来指定想要显示的字段
excludes：来指定不想要显示的字段 

组合查询: GET {{url}}/{{index}}/_search
请求参数
>{
    "query": {
        "bool": {
            "must": [
                {
                    "match": {
                        "name": "braun"
                    }
                }
            ],
            "must_not": [
                {
                    "match": {
                        "age": "180"
                    }
                }
            ],
            "should": [
                {
                    "match": {
                        "sex": "kurtis77"
                    }
                }
            ]
        }       
    }
}

范围查询: GET {{url}}/{{index}}/_search

请求参数:
>{
    "query": {
        "range": {
            "age": {
                "gte": 1,
                "lte": 1000
            }
        }
    }
}

range 查询找出那些落在指定区间内的数字或者时间。range 查询允许以下字符
> gt 大于、 lt 小于、gte 大于等于、 lte小于等于


模糊查询： GET {{url}}/{{index}}/_search

请求参数：
>{
    "query": {
        "fuzzy": {
            "name": {
                "value": "zhangsan"
            }
        }
    }
}

返回包含与搜索字词相似的字词的文档。
编辑距离是将一个术语转换为另一个术语所需的一个字符更改的次数。这些更改可以包括：
更改字符（box → fox）
删除字符（black → lack）
插入字符（sic → sick）
转置两个相邻字符（act → cat）
为了找到相似的术语，fuzzy 查询会在指定的编辑距离内创建一组搜索词的所有可能的变体或扩展。然后查询返回每个扩展的完全匹配。
通过 fuzziness 修改编辑距离。一般使用默认值 AUTO，根据术语的长度生成编辑距离


单字段排序: GET {{url}}/{{index}}/_search
请求参数：
>{
"query": {
"match_all": {

        }
    },
    "sort": [
        {
            "age": {
                "order": "desc"
            }
        }
    ]
}

多字段排序：GET {{url}}/{{index}}/_search
>{
    "query": {
        "match_all": {}
    },
    "sort": [
        {
            "age": {
                "order": "desc"
                }
        },
        {
            "_score": {
                "order": "desc"
            }
        }
    ]
}


高亮查询 GET {{url}}/{{index}}/_search
请求参数：
>{
    "query": {
        "match": {
            "name": "kohler"
        }
    },
    "highlight": {
            "pre_tags": "<font color='red'>",
            "post_tags": "</font>",
            "fields": {
            "name": {}
        }
    }
}

在进行关键字搜索时，搜索出的内容中的关键字会显示不同的颜色，称之为高亮。
Elasticsearch 可以对查询内容中的关键字部分，进行标签和样式(高亮)的设置。
在使用 match 查询的同时，加上一个 highlight 属性：
pre_tags：前置标签
post_tags：后置标签
fields：需要高亮的字段
title：这里声明 title 字段需要高亮，后面可以为这个字段设置特有配置，也可以空

分页查询：GET {{url}}/{{index}}/_search
请求参数：
>{
    "query": {
        "match_all": {}
    },
    "sort": [
        {
            "age": {
                "order": "desc"
            }
        }
    ],
    "from": 0,
    "size": 10
}


































































































































































































































































































































































































































































































































































































































































































