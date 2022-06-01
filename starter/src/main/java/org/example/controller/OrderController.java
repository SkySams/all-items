package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.example.annotation.OperationLog;
import org.example.encapsulat.Result;
import org.example.entiry.model.AppointmentForm;
import org.example.service.OrderService;
import org.example.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Slf4j
@Api(tags = "ORDER")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Qualifier("CustomServiceImpl")
    @Autowired
    private OrderService orderService;

    @ApiOperation("DAY")
    @GetMapping("/{day}")
    public Result<Date> getForDay(@PathVariable @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date day, Model model) {
        return Result.success(day);
    }

    @ApiOperation("ADD")
    @PostMapping("/app")
    public Result add(@RequestBody @Valid  AppointmentForm appointment) throws Exception {
        return  Result.success("");
    }


    @OperationLog(value = "测试")
    @ApiOperation("ONE")
    @GetMapping("order")
    public Result<String> one(@RequestParam String name){
        orderService.play();
        return Result.success("success");
    }

}
