package com.programmerzamannow.spring.config.springbootmessagesource;

import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import java.util.Locale;
// belajar Spring boot Message Source
@SpringBootTest (classes = SpringBootMessageSourceTest.TestApplication.class)
public class SpringBootMessageSourceTest {

    @Autowired
    private TestApplication.SampleSource sampleSource;

    @Test
    void testHelloAidil() {
        Assertions.assertEquals("Hello Aidil", sampleSource.helloAidil(Locale.ENGLISH));
        Assertions.assertEquals("Halo Aidil", sampleSource.helloAidil(new Locale("in_ID")));
    }

    @SpringBootApplication
    public static class TestApplication {

        @Component
        public static class SampleSource implements MessageSourceAware {
            // perlu setter untuk implementasinya
            @Setter
            private MessageSource messageSource;
            // buat methodnya dan biar gampang ngetestnya localnya kita kirim dari parameter aja Locale
            public String helloAidil(Locale locale){
               return messageSource.getMessage("hello", new  Object[]{"Aidil"}, locale);
            }


        }

    }
}
