package de.novatec.bpm;

import javax.inject.Named;
import org.camunda.bpm.engine.ProcessEngineException;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Named("deserialize")
public class DeserializeDelegate {

  private final Logger logger = LoggerFactory.getLogger(DeserializeDelegate.class);

  public void run(DelegateExecution execution) throws Throwable {
    try {
      Object object = execution.getVariableTyped("object").getValue();  // explicitly deserialize variable
      logger.info("Object successfully deserialized: " + object.toString());
    } catch (ProcessEngineException e) {
      if (e.getMessage().contains("Cannot deserialize object")) {
        if (e.getCause() != null && e.getCause().getCause() != null) {
          // explicitly log the spin error below if present
          logger.error("Cannot deserialize: " + e.getCause().getCause().getMessage());
          throw e.getCause().getCause();
        } else {
          logger.error("Cannot deserialize: " + e.getMessage());
          throw e;
        }
      } else {
        throw e;
      }
    }
  }
}
