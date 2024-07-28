package com.programmerzamannow.spring.config.value;

import lombok.Getter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

// belajar value

@SpringBootTest(classes = ValueTest.TestApplication.class)
public class ValueTest {

  @Autowired
  private TestApplication.ApplicationProperties properties;

  @Autowired
  private TestApplication.SystemProperties systemProperties;

  @Test
  void testValue() {
    Assertions.assertEquals("Belajar Spring Boot", properties.getName());
    Assertions.assertEquals(1, properties.getVersion());
    Assertions.assertEquals(false, properties.isProductionMode());
  }

  @Test
  void testSystemProperties() {
    Assertions.assertEquals("C:/Program Files/Java/jdk-17.0.8", systemProperties.getJavaHome());
  }

  @SpringBootApplication
  public static class TestApplication {

    @Component
    @Getter
    public static class SystemProperties {

      @Value("${JAVA_HOME}")
      private String javaHome;

    }

    @Component
    @Getter
    public static class ApplicationProperties {

      @Value("${application.name}")
      private String name;

      @Value("${application.version}")
      private Integer version;

      @Value("${application.production-mode}")
      private boolean productionMode;

    }

  }

}
