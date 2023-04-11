# Spring PetClinic Sample Application with GraalVM native image and Application Insights

This project illustrates how to use Application Insights with the Petclinic application built as a native image.

To try this project:
1) Provide a [connection string](https://learn.microsoft.com/en-us/azure/azure-monitor/app/java-standalone-config#connection-string) to an Application Insights resource in the [application.properties]([src/main/resources/application.properties](https://github.com/jeanbisutti/spring-petclinic/blob/5e4dffb736063198209c7840f1174a15a818b12d/src/main/resources/application.properties#L1)) file.
2) Create a native image: `mvn -Pnative spring-boot:build-image -Dspring-boot.build-image.imageName=petclinic-ai-native`
3) Run the native image: `docker run -p 8080:8080 petclinic-ai-native`
