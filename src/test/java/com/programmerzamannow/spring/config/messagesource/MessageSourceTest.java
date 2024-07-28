package com.programmerzamannow.spring.config.messagesource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

// belajar Message Source
public class MessageSourceTest {
    private ApplicationContext applicationContext;
    private MessageSource messageSource;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(TestApplication.class);
        messageSource = applicationContext.getBean(MessageSource.class);
    }

    @Test
    void testDefaultLocale() {
        String message = messageSource.getMessage("hello", new Object[]{"Aidil"}, Locale.ENGLISH);
        Assertions.assertEquals("Hello Aidil", message);
    }

    @Test
    void testIndonesianLocale() {
        String message = messageSource.getMessage("hello", new Object[]{"Aidil"}, new Locale("in_ID"));
        Assertions.assertEquals("Halo Aidil", message);
    }

    @SpringBootApplication
    public static class TestApplication{
        @Bean
        public MessageSource messageSource (){
            ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
            //karena kita bikin my.properties dan my_in_ID maka disini kita set
            messageSource.setBasenames("my");
            return messageSource;
        }
    }
}
