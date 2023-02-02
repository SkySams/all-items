package com.example.basicspring.message;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @author: zyh
 * @date: 2023/2/2
 */

@Component
public class LocaleMessage {
    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code) {
        return this.getMessage(code, new Object[]{});
    }

    public String getMessage(String code, String defaultMessage) {
        return this.getMessage(code, null, defaultMessage);
    }

    public String getMessage(String code, String defaultMessage, Locale locale) {
        return this.getMessage(code, null, defaultMessage, locale);
    }

    public String getMessage(String code, Locale locale) {
        return this.getMessage(code, null, "", locale);
    }

    public String getMessage(String code, Object[] args) {
        return this.getMessage(code, args, "");
    }

    public String getMessage(String code, Object[] args, Locale locale) {
        return this.getMessage(code, args, "", locale);
    }

    public String getMessage(String code, Object[] args, String defaultMessage) {
        return this.getMessage(code, args, defaultMessage, LocaleContextHolder.getLocale());
    }

    public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
        String value = messageSource.getMessage(code, args, defaultMessage, locale);
        return value;
    }
}
