package com.programmerzamannow.spring.config.configurationproperties;

//import com.programmerzamannow.spring.config.converter.StringToDateConverter;
import com.programmerzamannow.spring.config.converter.StringToDateConverter;
import com.programmerzamannow.spring.config.properties.ApplicationProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.convert.ApplicationConversionService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;


// belajar Configuration Properties
@SpringBootTest(classes = ConfigurationPropertiesTest.TestApplication.class)
public class ConfigurationPropertiesTest {

  @Autowired
  private ApplicationProperties properties;

  // belajar conversion service
  // menggunakan secara programmatically untuk melakukan konversi tipe data
  @Autowired
  private ConversionService conversionService;
  @Test
  void testConversionService() {
    Assertions.assertTrue(conversionService.canConvert(String.class, Duration.class));
    Assertions.assertTrue(conversionService.canConvert(String.class, Date.class));

    Duration result = conversionService.convert("10s", Duration.class);
    Assertions.assertEquals(Duration.ofSeconds(10), result);
  }

  // belajar Configuration Properties
  @Test
  void testConfigurationProperties() {
    Assertions.assertEquals("Belajar Spring Boot", properties.getName());
    Assertions.assertEquals(1, properties.getVersion());
    Assertions.assertEquals(false, properties.isProductionMode());
  }

  // belajar Complex object Configuration Properties
  @Test
  void testDatabaseProperties() {
    Assertions.assertEquals("aidil", properties.getDatabase().getUsername());
    Assertions.assertEquals("rahasia", properties.getDatabase().getPassword());
    Assertions.assertEquals("belajar", properties.getDatabase().getDatabase());
    Assertions.assertEquals("jdbc:contoh", properties.getDatabase().getUrl());
  }
  // belajar Collection Configuration Properties
  @Test
  void testCollection() {
    Assertions.assertEquals(Arrays.asList("products", "customers", "categories"), properties.getDatabase().getWhitelistTables());
    Assertions.assertEquals(100, properties.getDatabase().getMaxTablesSize().get("products"));
    Assertions.assertEquals(100, properties.getDatabase().getMaxTablesSize().get("categories"));
    Assertions.assertEquals(100, properties.getDatabase().getMaxTablesSize().get("customers"));
  }
// belajar Embedded Collection
  @Test
  void testEmbeddedCollection() {
    Assertions.assertEquals("default", properties.getDefaultRoles().get(0).getId());
    Assertions.assertEquals("Default Role", properties.getDefaultRoles().get(0).getName());
    Assertions.assertEquals("guest", properties.getDefaultRoles().get(1).getId());
    Assertions.assertEquals("Guest Role", properties.getDefaultRoles().get(1).getName());

    Assertions.assertEquals("admin", properties.getRoles().get("admin").getId());
    Assertions.assertEquals("Admin Role", properties.getRoles().get("admin").getName());
    Assertions.assertEquals("finance", properties.getRoles().get("finance").getId());
    Assertions.assertEquals("Finance Role", properties.getRoles().get("finance").getName());
  }
  // belajar conversion
  @Test
  void testDuration() {
    Assertions.assertEquals(Duration.ofSeconds(10), properties.getDefaultTimeout());
  }
  // belajar convesion custom
  // harus diregistrasikan ke conversion service agar tidak error
  @Test
  void testCustomConverter() {
    Date expireDate = properties.getExpireDate();

    var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Assertions.assertEquals("2020-10-10", dateFormat.format(expireDate));
  }

  @SpringBootApplication
  @EnableConfigurationProperties({
      ApplicationProperties.class
  })

  // belajar conversion service
  // meregistrasikan conversion service
   @Import(StringToDateConverter.class)
  public static class TestApplication {

    @Bean
    public ConversionService conversionService(StringToDateConverter stringToDateConverter){
      ApplicationConversionService conversionService = new ApplicationConversionService();
      conversionService.addConverter(stringToDateConverter);
      return conversionService;
    }

  }

}
