Instrumentation with Javaassist
=========

- Used Spark to handle HTTP requests

### Howto run

```
mvn clean integration-test
```
During unit test, you shouldn't see any `[AUDIT]` output in console.

What works:
- @PerformanceAudit annotation
- Http server runned during tests

What is not working:
- Showing parameters of method