package com.programmerzamannow.spring.config.environment;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

// belajar environment
// di materi ini diajarkan grep java home via terminal alt + f12 ketik env | grep JAVA_HOME

@SpringBootTest(classes = EnvironmentTest.TestApplication.class)
public class EnvironmentTest {

  @Autowired
  private Environment environment;

  @Test
  void testEnvironment() {
    String javaHome = environment.getProperty("JAVA_HOME");
    Assertions.assertEquals("C:/Program Files/Java/jdk-17.0.8", javaHome);
  }

  @SpringBootApplication
  public static class TestApplication {

  }
}
