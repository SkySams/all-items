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
 * @date: 2022/12/13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "elastic_search_db")
public class ElasticsearchDB {


    /**

     @Field(type=FieldType.Date)
     "date_optional_time||epoch_millis",
     @Field(type=FieldType.Date, format=DateFormat.basic_date)
     "basic_date"
     @Field(type=FieldType.Date, format={DateFormat.basic_date, DateFormat.basic_time})
     "basic_date||basic_time"
     @Field(type=FieldType.Date, pattern="dd.MM.uuuu")
     "date_optional_time||epoch_millis||dd.MM.uuuu",
     @Field(type=FieldType.Date, format={}, pattern="dd.MM.uuuu")
     "dd.MM.uuuu"

     */
    @Id
    private String pid;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Long)
    private long age;

    @Field(type = FieldType.Text)
    private String address;

    @Field(type = FieldType.Text)
    private String sex;

    @Field(type = FieldType.Text)
    private String remark;


}
