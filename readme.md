# Spring PetClinic Sample Application [![Build Status](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml/badge.svg)](https://github.com/spring-projects/spring-petclinic/actions/workflows/maven-build.yml)

## Heap allocation at start-up

| -                                         |            Main thread (1)             | Attach Listener thread (2) | Estimation for all threads (3) |   Residual heap    | Residual off-heap |
|:------------------------------------------|:--------------------------------------:|:--------------------------:|:------------------------------:|:------------------:|:-----------------:|
| Spring Boot                               | ~528.45 Mega bytes (554 124 648 bytes) |             0              |           ~ 700 MiB            | ~31 000 000  bytes |~116 000 000 bytes |
| Spring Boot + AI 3.3.1 agent              | ~979 Mega bytes (1 026 698 712 bytes)  |             0              |           ~ 1,2 GiB            | ~87 000 000 bytes  |~167 000 000 bytes |
| Spring Boot + AI runtime attachment 3.3.1 | ~980 Mega bytes (1 028 513 400 bytes)  |  ~226 796 944 bytes        |           ~ 1,5 GiB            |                    |                   |

(1) Measure with QuickPerf (@MeasureHeapAllocation - ByteWatcher)

(2) Measure with ByteWatcher

(3) Estimation with @ProfileJvm (QuickPerf): aggregation of TLAB and oustside TLAB JFR events

## Residual memory levels after GC

| -                                         |   Residual heap    | Residual off-heap |
|:------------------------------------------|:------------------:|:-----------------:|
| Spring Boot                               | ~31 000 000  bytes |~116 000 000 bytes |
| Spring Boot + AI 3.3.1 agent              | ~87 000 000 bytes  |~167 000 000 bytes |
