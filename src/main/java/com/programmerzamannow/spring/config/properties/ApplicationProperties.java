package com.programmerzamannow.spring.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties("application")
public class ApplicationProperties {
  // belajar Configuration Properties
  private String name;
  private Integer version;

  private boolean productionMode;

  // belajar Complex object Configuration Properties (embeded class)
  private DatabaseProperties database;

  //  belajar Embedded Collection
  private List<Role> defaultRoles;

  private Map<String, Role> roles;

  // belajar Conversion
  private Duration defaultTimeout;
  // belajar Conversion Custom
  private Date expireDate;


  @Getter
  @Setter
  public static class DatabaseProperties {
    // belajar Complex object Configuration Properties
    private String username;

    private String password;

    private String database;

    private String url;

    // belajar Collection Configuration Properties
    private List<String> whitelistTables;

    private Map<String, Integer> maxTablesSize;

  }


  @Getter
  @Setter
  public static class Role {
    // belajar Embedded Collection
    private String id;
    private String name;

  }
}
