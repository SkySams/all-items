package org.example.entity.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author: zyh
 * @date: 2022/4/8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "shop")
public class ProductEs {

    @Id
    private String pid;
    /**
     * type 指定数据类型   test支持分词  analyzer 指定分词  searchAnalyzer 指定搜索分词器
     */
    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Double)
    private Double marketprice;

    @Field(type = FieldType.Double)
    private Double shopprice;

    @Field(type = FieldType.Text)
    private String image;

    @Field(type = FieldType.Text )
    private String description;

    @Field(type = FieldType.Integer)
    private Integer ishot;

}
