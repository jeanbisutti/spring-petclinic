# Spring PetClinic Sample Application [![Build Status](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml/badge.svg)](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml)

## Heap allocation at start-up

| -                            |                Main thread                | Attach Listener thread |
|------------------------------|:-----------------------------------------:|:----------------------:|
| Spring Boot                  |  ~528.45 Mega bytes (554 124 648 bytes)   |           0            |
| Spring Boot + AI 3.3.1 agent |  ~979.14 Mega bytes (1 026 698 712 bytes) |           0            |
