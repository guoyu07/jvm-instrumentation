Instrumentation with Javaassist
=========

- Used Spark to handle HTTP requests

### Howto run

```
mvn clean integration-test
```
During unit test, you shouldn't see any `[AUDIT]` output in console.

### What works
- `@PerformanceAudit` annotation
- Http server runned during tests
- Showing parameters of method

### Output

#### Unit tests

```
agentTestWithoutPerformanceAnnotation
agentSlowTestWithPerformanceAnnotation
agentSlowTestWithPerformanceAnnotation

Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.26 sec
```

#### Integration tests
```
agentTestWithoutPerformanceAnnotation
agentSlowTestWithPerformanceAnnotation

 [AUDIT][ARGS] {:name=Joe}
 [AUDIT][getJoe] Method elapsedTime = 1000

agentSlowTestWithPerformanceAnnotation

 [AUDIT][ARGS] {:name=fastparam}
 [AUDIT][getJoe] Method elapsedTime = 0
 
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 3.056 sec - in net.piotrl.jvm.instr.InstrIT
```