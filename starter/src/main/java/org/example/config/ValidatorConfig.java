package org.example.config;

import org.springframework.context.annotation.Configuration;


import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author Administrator
 */
@Configuration
public class ValidatorConfig {

    /**
     * 没起作用
     * @return
     */
//    @Bean
//    public Validator localValidatorFactoryBean(){
//        return new LocalValidatorFactoryBean();
//    }

    /**
     * 没起作用
     */
//    @Bean
//    public Validator validator(){
//
//        ValidatorFactory validatorFactory= Validation.byProvider(HibernateValidator.class)
//                .configure()
//                .failFast(true)
//                .buildValidatorFactory();
//        return validatorFactory.getValidator();
//    }
}
