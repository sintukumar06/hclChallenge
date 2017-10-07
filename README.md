# Spring Boot "Microservice" Project(hclChallenge)
This is a sample Java / Maven / Spring Boot application that can be used as a starter for creating a microservice.

## How to Run

This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar target/spring-boot-rest-assingment-1.0-SNAPSHOT.jar
or
        mvn spring-boot:run
```