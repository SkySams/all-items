package org.example.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author: zyh
 * @date: 2022/3/7
 */
@Data
@Document(indexName = "employee")
public class Employee {

    @Id
    private String id;
    @Field(type = FieldType.Keyword)
    private String firstName;
    private String lastName;
    private Integer age = 0;
    private String about;

}
