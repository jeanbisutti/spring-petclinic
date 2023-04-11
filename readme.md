# Spring PetClinic Sample Application with GraalVM native image and Application Insights[![Build Status](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml/badge.svg)](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml)

This project illustrates how to use Application Insights with the Petclinic application built as a native image.

To try this project:
1) Provide a [connection string](https://learn.microsoft.com/en-us/azure/azure-monitor/app/java-standalone-config#connection-string) to an Application Insights resource in the [application.properties](src/main/resources/application.properties) file.
2) Create a native image: `mvn -Pnative spring-boot:build-image -Dspring-boot.build-image.imageName=petclinic-ai-native`
3) Run the native image: `docker run -p 8080:8080 petclinic-ai-native`
