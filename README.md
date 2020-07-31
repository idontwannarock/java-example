# Requirement

- JDK 11.0.6+
- Maven 3.6.x+

# Logging Required Properties

Add below properties to `log4j.properties` file in `src/test/resources` folder to log test info in your console.

```properties
log4j.rootLogger=DEBUG, A1
log4j.appender.A1=org.apache.log4j.ConsoleAppender
log4j.appender.A1.layout=org.apache.log4j.PatternLayout
log4j.appender.A1.layout.ConversionPattern=%d{ISO8601} [%c] %p: %m%n
log4j.category.com.monitorjbl=DEBUG
```

# Q & A

1. why ide does not recognize source and test classpath

a: mark src/main/java as sources root, src/test/java as test sources root