package org.example.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author: zyh
 * @date: 2022/3/7
 */
@Data
@Document(indexName = "employee")
public class Employee {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private Integer age = 0;
    private String about;

}
