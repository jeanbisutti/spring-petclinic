FROM  eclipse-temurin:11
ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

COPY profile-ai.jfc profile-ai.jfc

ENV APPLICATIONINSIGHTS_CONNECTION_STRING=""

# JFR
CMD ["java", "-Xms400m", "-Xmx400m", "-XX:StartFlightRecording=settings=profile-ai.jfc,filename=/tmp/ai-startup-bench.jfr,dumponexit=true", "-jar", "/app.jar"]
