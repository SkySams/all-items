package org.example.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.xiaoleilu.hutool.bean.BeanUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.model.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.Map;


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
    public ResponseBodyEmitter handle(@RequestParam String username) throws IOException {
        ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        // In some other thread
        emitter.send(username);
        emitter.complete();
        return emitter;
    }

    /**

     {
     "randomAbbreviation": "{{$randomAbbreviation}}",
     "randomAbstractImage": "{{$randomAbstractImage}}",
     "randomAdjective": "{{$randomAdjective}}",
     "randomAvatarImage": "{{$randomAvatarImage}}",
     "randomBankAccount": "{{$randomBankAccount}}",
     "randomBankAccountBic": "{{$randomBankAccountBic}}",
     "randomBankAccountIban": "{{$randomBankAccountIban}}",
     "randomBankAccountName": "{{$randomBankAccountName}}",
     "guid": "{{$guid}}",
     "isoTimestamp": "{{$isoTimestamp}}",
     "randomAlphaNumeric": "{{$randomAlphaNumeric}}",
     "randomBitcoin":"{{$randomBitcoin}}",
     "randomCommonFileType": "{{$randomCommonFileType}}",
     "randomWeekday": "{{$randomWeekday}}",
     "randomVerb":"{{$randomVerb}}",
     "randomUUID": "{{$randomUUID}}",
     "randomUserName":"{{$randomUserName}}",
     "randomUserAgent":"{{$randomUserAgent}}",
     "randomUrl": "{{$randomUrl}}",
     "randomTransportImage": "{{$randomTransportImage}}",
     "randomTransactionType": "{{$randomTransactionType}}",
     "randomStreetName": "{{$randomStreetName}}",
     "randomStreetAddress": "{{$randomStreetAddress}}",
     "randomSportsImage": "{{$randomSportsImage}}",
     "randomSemver":"{{$randomSemver}}",
     "randomProtocol":"{{$randomProtocol}}",
     "randomProductName":"{{$randomProductName}}",
     "randomProductMaterial":"{{$randomProductMaterial}}",
     "randomProductAdjective":"{{$randomProductAdjective}}",
     "randomProduct":"{{$randomProduct}}",
     "randomPrice":"{{$randomPrice}}",
     "randomPhrase":"{{$randomPhrase}}",
     "randomPhoneNumberExt":"{{$randomPhoneNumberExt}}",
     "randomPhoneNumber":"{{$randomPhoneNumber}}",
     "randomPeopleImage":"{{$randomPeopleImage}}",
     "randomPassword":"{{$randomPassword}}",
     "randomNoun":"{{$randomNoun}}",
     "randomNightlifeImage":"{{$randomNightlifeImage}}",
     "randomNatureImage":"{{$randomNatureImage}}",
     "randomNameSuffix":"{{$randomNameSuffix}}",
     "randomNamePrefix":"{{$randomNamePrefix}}",
     "randomMonth":"{{$randomMonth}}",
     "randomMimeType":"{{$randomMimeType}}",
     "randomMACAddress":"{{$randomMACAddress}}",
     "randomLoremWords":"{{$randomLoremWords}}",
     "randomLoremWord":"{{$randomLoremWord}}",
     "randomLoremText":"{{$randomLoremText}}",
     "randomLoremSlug":"{{$randomLoremSlug}}",
     "randomLongitude":"{{$randomLongitude}}",
     "randomLocale":"{{$randomLocale}}",
     "randomLatitude":"{{$randomLatitude}}",
     "randomLastName":"{{$randomLastName}}",
     "randomJobType":"{{$randomJobType}}",
     "randomJobTitle":"{{$randomJobTitle}}",
     "randomJobDescriptor":"{{$randomJobDescriptor}}",
     "randomJobArea":"{{$randomJobArea}}",
     "randomIPV6":"{{$randomIPV6}}",
     "randomIP":"{{$randomIP}}",
     "randomInt":"{{$randomInt}}",
     "randomIngverb":"{{$randomIngverb}}",
     "randomImageUrl":"{{$randomImageUrl}}",
     "randomImageDataUri":"{{$randomImageDataUri}}",
     "randomHexColor":"{{$randomHexColor}}",
     "randomFullName":"{{$randomFullName}}",
     "randomFoodImage":"{{$randomFoodImage}}",
     "randomFirstName":"{{$randomFirstName}}",
     "randomFileType":"{{$randomFileType}}",
     "randomFilePath":"{{$randomFilePath}}",
     "randomFileName":"{{$randomFileName}}",
     "randomFileExt":"{{$randomFileExt}}",
     "randomFashionImage":"{{$randomFashionImage}}",
     "randomExampleEmail":"{{$randomExampleEmail}}",
     "randomEmail":"{{$randomEmail}}",
     "randomDomainWord":"{{$randomDomainWord}}",
     "randomDomainSuffix":"{{$randomDomainSuffix}}",
     "randomDomainName":"{{$randomDomainName}}",
     "randomDirectoryPath":"{{$randomDirectoryPath}}",
     "randomDepartment":"{{$randomDepartment}}",
     "randomDateRecent":"{{$randomDateRecent}}",
     "randomDatePast":"{{$randomDatePast}}",
     "randomDateFuture":"{{$randomDateFuture}}",
     "randomDatabaseType":"{{$randomDatabaseType}}",
     "randomDatabaseEngine":"{{$randomDatabaseEngine}}",
     "randomDatabaseColumn":"{{$randomDatabaseColumn}}",
     "randomDatabaseCollation":"{{$randomDatabaseCollation}}",
     "randomCurrencySymbol":"{{$randomCurrencySymbol}}",
     "randomCurrencyName":"{{$randomCurrencyName}}",
     "randomCurrencyCode":"{{$randomCurrencyCode}}",
     "randomCreditCardMask":"{{$randomCreditCardMask}}",
     "randomCountryCode":"{{$randomCountryCode}}",
     "randomCountry":"{{$randomCountry}}",
     "randomCompanySuffix":"{{$randomCompanySuffix}}",
     "randomCompanyName":"{{$randomCompanyName}}",
     "randomCommonFileName":"{{$randomCommonFileName}}",
     "randomCommonFileExt":"{{$randomCommonFileExt}}",
     "randomColor":"{{$randomColor}}",
     "randomCityImage":"{{$randomCityImage}}",
     "randomCity":"{{$randomCity}}",
     "randomCatsImage":"{{$randomCatsImage}}",
     "randomCatchPhraseNoun":"{{$randomCatchPhraseNoun}}",
     "randomCatchPhraseDescriptor":"{{$randomCatchPhraseDescriptor}}",
     "randomCatchPhraseAdjective":"{{$randomCatchPhraseAdjective}}",
     "randomCatchPhrase":"{{$randomCatchPhrase}}",
     "randomBusinessImage":"{{$randomBusinessImage}}",
     "randomBsNoun":"{{$randomBsNoun}}",
     "randomBsBuzz":"{{$randomBsBuzz}}",
     "randomBsAdjective":"{{$randomBsAdjective}}",
     "randomBs":"{{$randomBs}}",
     "randomBoolean":"{{$randomBoolean}}"

     }

     * @param map
     * @return
     */
    @ApiOperation("POSTMAN")
    @PostMapping("/postman")
    public Map<Object, Object> map (@RequestBody Map<Object,Object> map){
        return map;
    }



}
