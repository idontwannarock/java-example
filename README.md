# Required Dependency

- assertj-core 3.11.1
- gson 2.8.5
- hamcrest-core 1.3
- hamcrest-library 1.3
- junit 4.12
- lombok 1.18.8
- opencsv 4.0
- Apache commons-net 3.6
- poi 3.17
- poi-ooxml 3.17
- poi-ooxml-schema 3.17
- xlsx-streamer 2.1.0
- log4j 1.2.16
- slf4j-api 1.7.29
- slf4j-log4j12 1.7.29
- xmlbeans 2.3.0
- jackson-core 2.10.0

# Required Properties

```properties
log4j.rootLogger=DEBUG, A1
log4j.appender.A1=org.apache.log4j.ConsoleAppender
log4j.appender.A1.layout=org.apache.log4j.PatternLayout
log4j.appender.A1.layout.ConversionPattern=%d{ISO8601} [%c] %p: %m%n

log4j.category.com.monitorjbl=DEBUG
```

# QA

1. ide does not recognize source and test path

a: mark src.main as sources root, test.main as sources root