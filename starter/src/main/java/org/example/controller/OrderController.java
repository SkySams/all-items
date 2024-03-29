package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.example.annotation.OperationLog;
import org.example.encapsulat.Result;
import org.example.entity.model.AppointmentForm;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Slf4j
@Api(tags = "ORDER")
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {



    @Qualifier("CustomServiceImpl")
    @Autowired
    private OrderService orderService;

    @Autowired
    private List<OrderService> orderServiceList;

    @ApiOperation("DAY")
    @GetMapping("/{day}")
    public Result<Date> getForDay(@PathVariable @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) Date day, Model model) {
        return Result.success(day);
    }

    @ApiOperation("ADD")
    @PostMapping("/app")
    public Result add(@RequestBody @Valid  AppointmentForm appointment) throws Exception {
        log.info("app:{}",request.getRequestURI());
        return  Result.success("");
    }


    @OperationLog(value = "测试")
    @ApiOperation("ONE")
    @GetMapping("order")
    public Result<String> one(@RequestParam String name){
        String sda = HtmlUtils.htmlUnescape(name);
        System.out.println(sda);
        orderService.play();
        return Result.success("success");
    }

    @ApiOperation(value = "repson")
    @GetMapping("repson")
    public Result<Respon> dmeo(){
        return Result.success(new Respon(null,"1","2"));
    }

}
