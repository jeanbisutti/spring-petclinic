# Application Insights start-up bench with JFR

## Custom JFR events

[Custom JFR events](./src/main/java/jfr) allow spotting the beginning and the end of Application Insights initialization.

## To execute the bench

1) Set the Application Insights connection string in [Dockerfile](./Dockerfile)

2) `mvn package -DskipTests`

3) `docker build . -t ai-startup-bench`

4) Create a Docker volume

5) To run the bench with half of a CPU: `docker run -it --cpus=0.5 -v {docker-volume-name}:/tmp ai-startup-bench`
