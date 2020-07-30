# Required Properties

Add below properties to log.properties file in src/main/resources folder.

```properties
log4j.rootLogger=DEBUG, A1
log4j.appender.A1=org.apache.log4j.ConsoleAppender
log4j.appender.A1.layout=org.apache.log4j.PatternLayout
log4j.appender.A1.layout.ConversionPattern=%d{ISO8601} [%c] %p: %m%n

log4j.category.com.monitorjbl=DEBUG
```

# QA

1. why ide does not recognize source and test classpath

a: mark src/main/java as sources root, src/test/java as test sources root