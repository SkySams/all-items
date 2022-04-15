# build  Elasticsearch/Logstash/Kibana
> https://www.elastic.co/guide/en/elasticsearch/reference/6.0/_basic_concepts.html

# Filebeat 
收集日集插件
Filebeat 是使用 Golang 实现的轻量型日志采集器，也是 Elasticsearch stack 里面的一员

Filebeat 启动：
filebeat -e -c filebeat.yml

## Elasticsearch basic
> 是文档存储索引
> Elasticsearch 是搜索平台（搜索延迟-秒）

1. 集群
>集群是一个或多个节点（服务器）的集合，它们一起保存您的整个数据并提供跨所有节点的联合索引和搜索功能。
> 集群由唯一名称标识，默认情况下为“elasticsearch”。此名称很重要，因为只有将节点设置为按其名称加入集群时，该节点才能成为集群的一部分

> 确保您不要在不同的环境中重复使用相同的集群名称，否则您最终可能会导致节点加入错误的集群。
>> 例如，您可以将logging-dev、logging-stage和logging-prod 用于开发、登台和生产集群。

>请注意，拥有一个只有一个节点的集群是有效且完全可以的。此外，您还可能有多个独立的集群，每个集群都有自己唯一的集群名称

2. 节点
> 索引是具有某种相似特征的文档的集合 


3. 指数
> 索引是具有某种相似特征的文档的集合。
> 例如，您可以有一个客户数据索引、另一个产品目录索引和另一个订单数据索引。索引由名称（必须全部小写）标识，
> 该名称用于在对其中的文档执行索引、搜索、更新和删除操作时引用索引。

> 在单个集群中，您可以定义任意数量的索引。

4. 类型
> 在6.0.0之后版本弃用

> 一种类型曾经是索引的逻辑类别/分区，允许您在同一索引中存储不同类型的文档，例如，一种类型用于用户，另一种类型用于博客文章。
> 不再可能在索引中创建多个类型，并且在以后的版本中将删除整个类型的概念。


5. 文档
> 文档是可以被索引的基本信息单元。例如，您可以为单个客户创建一个文档，为单个产品创建另一个文档，以及为单个订单创建另一个文档。

> 在索引/类型中，您可以存储任意数量的文档。请注意，尽管文档物理上驻留在索引中，但文档实际上必须被索引/分配给索引内的类型。

6. 碎片与副本
> 索引可能会存储大量数据，这些数据可能超出单个节点的硬件限制。
> 例如，占用 1TB 磁盘空间的十亿文档的单个索引可能不适合单个节点的磁盘，或者可能太慢而无法单独处理来自单个节点的搜索请求

> 为了解决这个问题，Elasticsearch 提供了将您的索引细分为多个称为分片的片段的能力。
> 创建索引时，您可以简单地定义所需的分片数量。每个分片本身就是一个功能齐全且独立的“索引”，可以托管在集群中的任何节点上

7. 分片之所以重要，主要有两个原因
   * 它允许您水平拆分/缩放内容量
   * 它允许您跨分片（可能在多个节点上）分布和并行化操作，从而提高性能/吞吐量

> 分片的分布机制以及其文档如何聚合回搜索请求的机制完全由 Elasticsearch 管理，并且对您作为用户是透明的。

>在随时可能出现故障的网络/云环境中，非常有用且强烈建议使用故障转移机制，以防分片/节点因某种原因脱机或消失。
> 为此，Elasticsearch 允许您将索引分片的一个或多个副本制作成所谓的副本分片或简称副本。

8. 复制之所以重要，主要有两个原因：
    * 它在分片/节点失败的情况下提供高可用性。出于这个原因，重要的是要注意，副本分片永远不会与从中复制它的原始/主分片分配在同一节点上。
    * 它允许您扩展搜索量/吞吐量，因为搜索可以在所有副本上并行执行

9. 总结
> 总而言之，每个索引都可以拆分为多个分片。索引也可以复制零次（意味着没有副本）或多次。
> 复制后，每个索引将具有主分片（从中复制的原始分片）和副本分片（主分片的副本）。可以在创建索引时为每个索引定义分片和副本的数量。
> 创建索引后，您可以随时动态更改副本数，但不能事后更改分片数。

>默认情况下，Elasticsearch 中的每个索引都分配有 5 个主分片和 1 个副本，这意味着如果您的集群中至少有两个节点，
>您的索引将有 5 个主分片和另外 5 个副本分片（1 个完整副本），总共每个索引 10 个分片。

10. 注意事项
> 每个 Elasticsearch 分片都是一个 Lucene 索引。在单个 Lucene 索引中可以拥有的文档数量是最大的。
> 截至LUCENE-5843，限制为2,147,483,519(= Integer.MAX_VALUE - 128) 个文档。_cat/shards您可以使用API监控分片大小。


# Test 
elastic 运行情况
GET /_cat/health?v
* 绿色 - 一切都很好（集群功能齐全）
* 黄色 - 所有数据都可用，但尚未分配一些副本（集群功能齐全）
* 红色 - 某些数据由于某种原因不可用（集群部分功能）

集群中的节点列表
GET /_cat/nodes?v&pretty

获取所有索引
GET /_cat/indices?v

创建索引
PUT /customer?pretty
GET /_cat/indices?v


在索引中添加数据
(/{index}/_doc/{id}, /{index}/_doc, or /{index}/_create/{id})
> 例如
PUT /customer/_doc/1?pretty
{
  "name": "Jack"
}

获取索引中id为1的数据
GET /customer/_doc/1?pretty

删除索引
DELETE /customer?pretty

## 增删改查
增加文件
PUT /{index}/_doc/{id}/pretty
> 会直接覆盖之前的文档
> Elasticsearch 提供近乎实时的数据操作和搜索功能。
> 默认情况下，从索引/更新/删除数据到它出现在搜索结果中的时间，您可以预期一秒钟的延迟（刷新间隔）。
> 这是与 SQL 等其他平台的重要区别，后者在事务完成后数据立即可用。


PUT /customer/_doc/1?pretty
{
"age": 133
}

更新文档
POST /customer/_doc/1/_update?pretty
>请注意，尽管 Elasticsearch 实际上并没有在后台进行就地更新。
>每当我们进行更新时，Elasticsearch 都会删除旧文档，然后索引一个新文档，并一次性应用更新。

POST /customer/_doc/1/_update?pretty
{
"doc": {"kong": "Hell23o", "age": 1}
}

POST /customer/_doc/1/_update?pretty
{
"script": "ctx._source.age += 5"
}

删除文档
DELETE /customer/doc/2?pretty

## 批量处理
> 格式很严，{} 不能分行
POST /customer/doc/_bulk?pretty
curl -X POST "localhost:9200/customer/doc/_bulk?pretty&pretty" -H 'Content-Type: application/json' -d'
{"index":{"_id":"1"}}
{"name": "John Doe" }
{"index":{"_id":"2"}}
{"name": "Jane Doe" }

POST /customer/doc/_bulk?pretty
{"update":{"_id":"1"}}
{"doc": { "name": "John Doe becomes Jane Doe" } }
{"delete":{"_id":"2"}}


POST /bank/_doc/_bulk?pretty
{"index":{"_id": "2"}}
{"account_number": 0,"name":"s","firstname": "Bradshaw"}


> 批量 API 不会因其中一项操作失败而失败。
> 如果单个操作因任何原因失败，它将继续处理之后的其余操作。
> 当批量 API 返回时，它将为每个操作提供一个状态（按照发送的顺序），以便您检查特定操作是否失败


## 搜索API REST API
### *第一种是REST 请求 URI
/bank/_search?q=*&sort=account_number:asc&pretty
> took: Elasticsearch 执行搜索的时间（以毫秒为单位）
> timed_out： 告诉我们搜索是否超时
> _shards– 告诉我们搜索了多少分片，以及搜索成功/失败的分片计数
> hits- 搜索结果
> hits.total– 符合我们搜索条件的文档总数
> hits.hits– 实际的搜索结果数组（默认为前 10 个文档）
> hits.sort- 结果的排序键（如果按分数排序则缺失）
> hits._score和max_score- 暂时忽略这些字段

### * REST 请求正文
GET /bank/_search
{
  "query": { "match_all": {} },
  "sort": [
       { "account_number": "asc" }
  ]
}

* 排序必须是数字类型

> 搜索全部

GET /bank/_search
{
    "query": {"match_all": {}},
    "sort": [
         {"account_number" :"asc"}
    ]
}
> 搜索name

GET /customer/_search
{
    "query": {"term": {"name":"jack"}}
}

>"_source": ["account_number","balance"] 它仍然只会返回一个名为_source但在其中的字段，仅包含字段account_number和balance。

GET /bank/_search
{
    "query": { "match_all": {} },
    "_source": [ "balance"]
}

> "match" : 匹配 account_number 下包含20的

GET /bank/_search
{
    "query": { "match": { "account_number": 20 } }
}

> match_phrase 此示例是match( match_phrase) 的变体

GET /bank/_search
{
"query": { "match_phrase": { "address": "Place" } }
}

> 同时组合must、should和must_not子句。bool此外，我们可以bool在任何这些bool子句中编写查询来模拟任何复杂的多级布尔逻辑

1. 布尔查询
> 匹配与其他查询的布尔组合匹配的文档的查询。 bool 查询映射到 Lucene BooleanQuery。 
> 它是使用一个或多个布尔子句构建的，每个子句都有一个类型的出现。出现类型有
   * must 子句（查询）必须出现在匹配的文档中，并将有助于得分
   * filter 子句（查询）必须出现在匹配的文档中。然而，与 must查询的分数不同，将被忽略。过滤器子句在过滤器上下文中执行，这意味着忽略评分并考虑缓存子句。
   * should 
>子句（查询）应该出现在匹配的文档中。如果 bool查询在查询上下文中并且有mustor filter子句，那么bool即使没有任何 should查询匹配，
>文档也会匹配查询。在这种情况下，这些子句仅用于影响分数。如果bool查询是过滤上下文 或两者都没有must，
>或者filter至少有一个should查询必须匹配文档，它才能匹配bool查询。此行为可以通过设置 minimum_should_match参数来显式控制。
   * must_not 子句（查询）不得出现在匹配的文档中。子句在过滤器上下文中执行，这意味着忽略评分并考虑缓存子句。因为忽略了评分，0所以返回所有文档的评分
> 该bool must子句指定了所有必须为真的查询才能使文档被视为匹配

GET /bank/_search
{
    "query": {
        "bool": {
            "must": [
                { "match": { "address": "244" } },
                { "match": { "address": "Columbus" } }
            ]
        }
    }
}

>该bool should子句指定了一个查询列表，其中任何一个都必须为真才能使文档被视为匹配。

GET /bank/_search
{
    "query": {
        "bool": {
        "should": [
                { "match": { "address": "mill" } },
                { "match": { "address": "lane" } }
            ]
        }
    }
}
> 该bool must_not子句指定了一个查询列表，其中没有一个必须为真才能将文档视为匹配。

GET /bank/_search
{
    "query": {
        "bool": {
            "must_not": [
                { "match": { "address": "mill" } },
                { "match": { "address": "lane" } }
            ]
        }
    }
}

> 执行过滤器 剖析以上内容，布尔查询包含一个match_all查询（查询部分）和一个range查询（过滤部分）。
> 我们可以将任何其他查询替换为查询和过滤器部分。

GET /bank/_search
{
    "query": {
        "bool": {
            "must": { "match_all": {} },
                "filter": {
                    "range": {
                        "balance": {
                        "gte": 20000,
                        "lte": 30000
                    }
                }
            }
        }
    }
}

* 


> 总结： 重要的是要了解，一旦您返回搜索结果，Elasticsearch 就会完全处理请求，并且不会维护任何类型的服务器端资源或在结果中打开游标。
> 这与许多其他平台（例如 SQL）形成鲜明对比，在 SQL 中，您最初可能会预先获得查询结果的部分子集，然后如果您想获取（或翻页）其余部分，
> 则必须不断返回服务器使用某种有状态的服务器端游标的结果。


## URi搜索
GET /_search
GET /customer/_search?q=name:JACK

[链接](https://www.elastic.co/guide/en/elasticsearch/reference/6.0/search-uri-request.html)
>q:查询字符串

>analyzer:分析查询字符串时要使用的分析器名称

>analyze_wildcard: 是否应该分析通配符和前缀查询。默认为false.

>batched_reduce_size: 应该在协调节点上一次减少的分片结果的数量。如果请求中的潜在分片数量可能很大，
则应将此值用作保护机制以减少每个搜索请求的内存开销。

>default_operator: 要使用的默认运算符可以是AND或 OR。默认为OR.

>lenient: 如果设置为 true 将导致基于格式的失败（例如向数字字段提供文本）被忽略。默认为假。

>explain: 对于每个命中，包含对如何计算命中得分的说明。

>_source: 设置为禁用字段false检索。您还可以使用&_source检索部分文档（ 有关详细信息，请参阅请求正文文档）_source_include_source_exclude

>stored_fields: 为每个命中返回的文档的选择性存储字段，逗号分隔。不指定任何值将导致没有字段返回。

>sort: 排序执行。可以是fieldName, 或 fieldName:asc/的形式fieldName:desc。fieldName 可以是文档中的实际字段，
也可以是_score表示基于分数排序的特殊名称。可以有多个sort参数（顺序很重要）。

>track_scores: 排序时，设置为true以便仍然跟踪分数并将它们作为每次命中的一部分返回。

>track_total_hits: 设置为false以禁用对匹配查询的命中总数的跟踪

>timeout: 搜索超时，限制要在指定时间值内执行的搜索请求，并在过期时使用累积到该点的命中来保释。默认为无超时

>terminate_after: 为每个分片收集的最大文档数，达到该数量后查询执行将提前终止。
如果设置，响应将有一个布尔字段terminated_early来指示查询执行是否实际上已终止_early。
默认为没有 terminate_after。

>from: 要返回的命中的起始索引。默认为0.

>size: 要返回的命中数。默认为10.

>search_type: 要执行的搜索操作的类型。可以是 dfs_query_then_fetch或query_then_fetch。
             默认为query_then_fetch. 有关可以执行的不同类型的搜索的更多详细信息，请参阅 搜索类型。


## 聚合执行

GET /bank/_search
{
    "size": 10,
    "aggs": {
        "group_by_state": {
            "terms": {
                "field": "state.keyword"
            }
        }
    }
}
> SELECT state, COUNT(*) FROM bank GROUP BY state ORDER BY COUNT(*) DESC

## 

















## 问题
* 存储类型有哪些？















































































