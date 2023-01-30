package com.example.basicspring.config.convert;

import com.example.basicspring.entity.vo.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonMixin;

/**
 * 非入侵式的
 * @author: zyh
 * @date: 2023/1/30
 */
@JsonMixin({Student.class})
public abstract class PhoneKeyConvert {

    @JsonProperty("phone")
    String mobile;

}
