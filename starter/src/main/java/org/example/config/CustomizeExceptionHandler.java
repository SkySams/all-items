package org.example.config;

import com.xiaoleilu.hutool.json.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/5/31
 */

@Slf4j
@RestControllerAdvice
public class CustomizeExceptionHandler {

    Map<String, Object> map = new HashMap<>();

//    @ExceptionHandler(value = ValidationException.class)
//    public Map<String, Object> methodArgumentNotValidException(HttpServletRequest request, HttpServletResponse response, ValidationException e) {
//        map.put("code", 500);
//        map.put("message", e.getMessage().split(":")[1]);
//        return map;
//    }


    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        map.put("code", 500);
        map.put("message",message);
        return map;
    }


}
