# Camunda Security Example
In some cases it is necessary to block certain data types from being deserialized by the spin api:
```
execution.getVariableTyped("myVariable").getValue();
```

## What needs to be done
- set property `setDeserializationTypeValidationEnabled(true)`
- set property `setDeserializationAllowedClasses(\"com.my.class.MyClass,com.my.class.MyOtherClass\")` to whitelist classes
- set property `setDeserializationAllowedPackages(\"com.my.class\")` to whitelist packages
### More power needed?
- provide a class which implements the interfaces `DeserializationTypeValidator` or `WhitelistingDeserializationTypeValidator`
- you can also extend the existing class `DefaultDeserializationTypeValidator` which already allows uncritical data types
- define the classes by full qualified name or packages which are allowed
- register the type validator in your engine configuration `setDeserializationTypeValidator(myValidator)`

## This example

In our case there are three classes `Car`, `Person` and `House` which can be put into the process as variable. 
The class `Person` is not allowed to be deserialized.

- start the application by running `./gradlew bootRun`
- use your favourite rest api client to call the application:
    - URI: http://localhost:8080 <br>
    - Request: POST <br>
    - Content-Type: application/json <br>
    - Payload: <br>
```
{
  "businessKey": "ac955d4e-4eae-4d9f-929e-b0352087f18d",
  "variables": {
    "object": {
      "value": "{ \"brand\" : \"bmw\", \"price\": 10000 }",
      "type": "Object",
      "valueInfo": {
        "objectTypeName": "de.novatec.bpm.model.Car",
        "serializationDataFormat": "application/json"
      }
    }
  }
}
```

- other examples are located under `src/main/resources`