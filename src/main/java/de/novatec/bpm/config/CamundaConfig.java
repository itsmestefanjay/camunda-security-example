package de.novatec.bpm.config;

import org.camunda.bpm.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.camunda.bpm.spring.boot.starter.configuration.impl.AbstractCamundaConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CamundaConfig extends AbstractCamundaConfiguration {

  private final Logger logger = LoggerFactory.getLogger(CamundaConfig.class);

  @Override
  public void preInit(ProcessEngineConfigurationImpl configuration) {
    // disable telemetry
    configuration.setInitializeTelemetry(false);
    configuration.setTelemetryReporterActivate(false);
    // enable deserialization type validation
    configuration.setDeserializationTypeValidationEnabled(true);
    configuration.setDeserializationAllowedClasses("de.novatec.bpm.model.Car,de.novatec.bpm.dao.House");
    configuration.setDeserializationTypeValidator(new DeserializeWhitelist());
    configuration.setEnableXxeProcessing(false);
    logger.info("deserialization allowed for classes {}", configuration.getDeserializationAllowedClasses());
  }

}
