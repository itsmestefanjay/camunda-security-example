package de.novatec.bpm.config;

import java.io.IOException;
import java.util.Collections;
import javax.sql.DataSource;
import org.camunda.bpm.engine.impl.jobexecutor.DefaultJobExecutor;
import org.camunda.bpm.engine.impl.persistence.StrongUuidGenerator;
import org.camunda.bpm.engine.spring.ProcessEngineFactoryBean;
import org.camunda.bpm.engine.spring.SpringProcessEngineConfiguration;
import org.camunda.spin.plugin.impl.SpinProcessEnginePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class CamundaConfig {

  @Autowired
  ResourcePatternResolver resolver;

  @Bean
  SpringProcessEngineConfiguration springProcessEngineConfiguration(ApplicationContext applicationContext, DataSource camundaBpmDataSource, PlatformTransactionManager transactionManager) throws IOException {
    SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();
    config.setDeserializationTypeValidationEnabled(true);
    config.setDeserializationTypeValidator(new DeserializeWhitelist());
    config.setEnableXxeProcessing(false);
    config.setTransactionManager(transactionManager);
    config.setDataSource(camundaBpmDataSource);
    config.setApplicationContext(applicationContext);
    config.setDatabaseSchemaUpdate("true");
    config.setProcessEnginePlugins(Collections.singletonList(new SpinProcessEnginePlugin()));
    config.setDefaultSerializationFormat("application/json");
    config.setJobExecutor(new DefaultJobExecutor());
    config.setJobExecutorActivate(true);
    config.setIdGenerator(new StrongUuidGenerator());
    Resource[] resources = resolver.getResources("classpath:bpmn/process.bpmn");
    config.setDeploymentResources(resources);
    return config;
  }

  @Bean
  ProcessEngineFactoryBean processEngineFactoryBean(SpringProcessEngineConfiguration springProcessEngineConfiguration) {
    ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
    processEngineFactoryBean.setProcessEngineConfiguration(springProcessEngineConfiguration);
    return processEngineFactoryBean;
  }

}
