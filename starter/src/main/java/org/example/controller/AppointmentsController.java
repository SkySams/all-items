package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.example.encapsulat.Result;
import org.example.entity.model.Account;
import org.example.entity.model.Pet;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: zyh
 * @date: 2022/5/31
 */
@Slf4j
@Api(tags = "APP")
@RestController
public class AppointmentsController {

    @ApiOperation("FIND")
    @GetMapping(value = "/owners/{ownerId}" ,produces = MediaType.APPLICATION_JSON_VALUE)
    public Result findOwner(@PathVariable String ownerId) {
        return Result.success(ownerId);
    }

    @ApiOperation("PET")
    @GetMapping("/owners/{ownerId}/pets/{petId}")
    public Result findPet(@PathVariable String ownerId, @PathVariable String petId) {
       return Result.success(petId);
    }

    @ApiOperation("DEME")
    @GetMapping("/demo/owners/{ownerId}/pets/{petId}")
    public void findPet1(
            @MatrixVariable(pathVar = "ownerId") MultiValueMap<String, String> matrixVars,
            @MatrixVariable(pathVar ="petId") MultiValueMap<String, String> petMatrixVars) {

        log.info("{}",matrixVars);
        log.info("{}",petMatrixVars);

   }

    @ApiOperation("SOME")
    @GetMapping("/something")
    public ResponseEntity<String> handle(HttpEntity<byte[]> requestEntity)   {
        byte[] requestBody = requestEntity.getBody();

        // do something with request header and body

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
    }

    @ApiOperation("PROCE")
    @PostMapping("/owners/{ownerId}/pets/{petId}/edit")
    public String processSubmit(@ModelAttribute Pet pet) {
        return "OK";
    }


    @ApiOperation("ACCOUNT")
    @PutMapping("/accounts/{account}")
    public String save(@ModelAttribute("account") Account account) {
        log.info("account: {}",account);
        return "success";
    }

    @ApiOperation("SUBMIT")
    @PostMapping("/owners/{ownerId}/pets/{petId}/edit/two")
    public String processSubmit(@Valid @ModelAttribute("pet") Pet pet, BindingResult result) {
        log.info("pet: {}",pet);
        return "seccess";
    }

    @ApiOperation("测试")
    @PostMapping("two")
    public String nice(@RequestBody @Valid Pet pet) {
        log.info("pet: {}",pet);
        return "seccess";
    }


}
