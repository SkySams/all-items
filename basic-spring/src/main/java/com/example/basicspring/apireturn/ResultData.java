package com.example.basicspring.apireturn;

import com.example.basicspring.enums.CodeEnum;
import lombok.Data;

/**
 * @author: zyh
 * @date: 2023/2/2
 */

@Data
public class ResultData<T> {
    private Integer code;
    private String message;
    private T data;

    public ResultData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResultData success(CodeEnum codeEnum) {
        return new ResultData(codeEnum.code, codeEnum.msg);
    }

    public static ResultData success(String msg) {
        return new ResultData(CodeEnum.SUCCESS.getCode(), msg);
    }
}