package de.novatec.bpm.config;

import de.novatec.bpm.dao.House;
import de.novatec.bpm.model.Car;
import java.util.Arrays;
import java.util.List;
import org.camunda.bpm.engine.runtime.DeserializationTypeValidator;
import org.camunda.bpm.engine.runtime.WhitelistingDeserializationTypeValidator;

public class DeserializeWhitelist implements DeserializationTypeValidator {

  // only allow explicit classes to be deserialized
  private final List<String> classes = Arrays.asList(
      Car.class.getName(),
      House.class.getName()
  );

  @Override
  public boolean validate(String className) {
    return classes.contains(className);
  }
}
