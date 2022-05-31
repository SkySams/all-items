package org.example.encapsulat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: zyh
 * @date: 2022/5/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private int code;

    private String message;

    private T data;

    @JsonIgnore
    private Throwable throwable;

    protected Result(int code, String message){
        this.code = code;
        this.message = message;
    }


    protected Result(int code, String message, T data){
        this(code, message);
        this.data = data;
    }
    /**
     * @desc 操作成功
     * @auther cwhisky-heyang
     * @date 2021/9/30 10:33
     */
    public  static <T> Result<T> success(T data){
        return new Result<>(200, "success",data);
    }




}
