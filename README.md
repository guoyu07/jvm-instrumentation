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
agentWithoutAnnotation
agentSlowWithAnnotation
agentFastWithAnnotation

Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.07 sec```
```

#### Integration tests

```
agentWithoutAnnotation
agentSlowWithAnnotation

 [AUDIT][ARGS] {:name=Joe}
 [AUDIT][getJoe] Method elapsedTime = 1000

agentFastWithAnnotation

 [AUDIT][ARGS] {:name=fastparam}
 [AUDIT][getJoe] Method elapsedTime = 0

Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.992 sec - in net.piotrl.jvm.instr.InstrIT
```