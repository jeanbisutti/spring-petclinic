# Spring PetClinic Sample Application [![Build Status](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml/badge.svg)](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml)

## Heap allocation at start-up

|                                           | Main thread  (1)                         | Attach Listener thread (2) | Estimation for all threads (3) |
|-------------------------------------------|:----------------------------------------:|:--------------------------:|:------------------------------:|
| Spring Boot + AI runtime attachment 3.3.1 | ~980.87 Mega bytes (1 028 513 400 bytes) |  ~226 796 944 bytes        |           ~ 1,5 GiB            |

(1) Measure with QuickPerf (@MeasureHeapAllocation - ByteWatcher)

(2) Measure with ByteWatcher

(3) Estimation with @ProfileJvm (QuickPerf): aggregation of TLAB and oustside TLAB JFR events
