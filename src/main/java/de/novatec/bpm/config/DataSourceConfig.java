package de.novatec.bpm.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataSourceConfig {

  @Bean
  @Primary
  @ConfigurationProperties(prefix="camunda.bpm.datasource")
  public DataSource camundaBpmDataSource() {
    return DataSourceBuilder.create().build();
  }

  @Bean
  public PlatformTransactionManager transactionManager(DataSource camundaBpmDataSource) {
    return new DataSourceTransactionManager(camundaBpmDataSource);
  }

}
