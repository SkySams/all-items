package org.example.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.example.entiry.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;

/**
 * @author: zyh
 * @date: 2022/6/1
 */
@Slf4j
@Api(tags = "FORM")
@RestController
public class EditPetFormController {

    @ApiOperation("GET")
    @GetMapping("/user")
    @JsonView(User.WithoutPasswordView.class)
    public User getUser() {
        return new User("eric", "nice");
    }

    @ApiOperation("QUOTES")
    @GetMapping("/quotes")
    @ResponseBody
    public DeferredResult<String> quotes() {
        DeferredResult<String> deferredResult = new DeferredResult<String>();
        // Save the deferredResult somewhere..
        deferredResult.setResult("ni");
        return deferredResult;
    }

    @ApiOperation("HAN")
    @GetMapping("/events")
    public ResponseBodyEmitter handle() throws IOException {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        // In some other thread
        emitter.send("Hello once");
        emitter.send("Hello again");

        emitter.complete();
        return emitter;
    }



}
