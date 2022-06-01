package org.example.util;

import com.github.xiaoymin.knife4j.core.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ValidateUtil {

    private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    public static void beanValidate(Object obj) throws Exception {
        Map<String, String> validatedMsg = new HashMap<>();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
        for (ConstraintViolation<Object> c : constraintViolations) {
            validatedMsg.put(c.getPropertyPath().toString(), c.getMessage());
        }
        if (CollectionUtils.isNotEmpty(constraintViolations)) {
            throw new Exception((Throwable) validatedMsg);
        }

    }

}
