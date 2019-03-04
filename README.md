[![CircleCI](https://circleci.com/gh/jbielak/emulator-api/tree/master.svg?style=svg)](https://circleci.com/gh/jbielak/emulator-api/tree/master)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/8f758edaf54e4894ad6692e08620e7b6)](https://www.codacy.com/app/jbielak/emulator-api?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=jbielak/emulator-api&amp;utm_campaign=Badge_Grade)

# Android Emulator API App

Android Emulator API is a Spring Boot application that enables REST communication with Android emulator running on localhost.

API is designed according to [Send Emulator console commands Guide](https://developer.android.com/studio/run/emulator-console).

## Running Emulator API locally

### Prerequisites
-   [Java Development Kit 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
-   Git (optional)

```
git clone https://github.com/jbielak/emulator-api.git
cd emulator-api
./gradlew build
java -jar target/*.jar
```

By default application is running on: 
```
localhost:8080
```

### Configuration 
Using above run command app is running with default configuration from `emulator-api/src/main/resources/application
.properties` file: 
```
#emulator socket connection properties
socket.emulator.address=127.0.0.1
socket.emulator.port=5554
```

Configuration properties:
-   **socket.emulator.address** - address of running Android Emulator
-   **socket.emulator.port** - port of running Android Emulator

In case you want to run the app with custom `*.properties` file create it using `application.properties` as your 
template and pass the path to your file while executing running the app command.
For example if the `*.properties` file is in the same directory with the application  `*.jar` file.

```
cd 'app.jar location'
java -jar app.jar --spring.config.location=file:./custom.properties
```

On startup the application is attempting to connect to Android Emulator running on specified address and port so make
 sure to **start the emulator**.
 
## API
-   [Emlulator Client](https://github.com/jbielak/emulator-api/tree/master/chapters/emulator-client.md)
-   [Authentication](https://github.com/jbielak/emulator-api/tree/master/chapters/authentication.md)

## Built With
-   [Java Development Kit 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
-   [Gradle 5.2.1](https://spring.io/guides/gs/gradle/)
-   [Spring Boot 2.1.3.RELEASE](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot/2.1.3.RELEASE)

## Test Libraries
-   [JUnit 5.4.0](https://junit.org/junit5/docs/current/api/)

-   [Mockito JUnit Jupiter 2.24.5](https://bintray.com/mockito/maven/mockito) provides an implementation for JUnit 5 
extensions
