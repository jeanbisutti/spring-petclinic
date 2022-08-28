FROM eclipse-temurin:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar
COPY agents/*.jar /agents/

COPY jvm-disable-c2.json jvm-disable-c2.json

ENV APPLICATIONINSIGHTS_CONNECTION_STRING=<CONNECTION_STRING>

# With C2 compiler
CMD ["java", "-javaagent:/agents/applicationinsights-agent-3.3.1.jar", "-jar", "/app.jar"]

# Without the C2 compiler during the agent setup
CMD ["java", "-javaagent:/agents/applicationinsights-agent-3.3.1.jar", "-XX:+UnlockDiagnosticVMOptions", "-XX:CompilerDirectivesFile=jvm-disable-c2.json", "-Dappplicationinsights.debug.jit.c2-optimization.enabled=true", "-jar", "/app.jar"]
