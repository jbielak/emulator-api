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
java -jar build/libs/*.jar
```

By default application is running on: 
```
localhost:8080
```

Base API url is:
```
localhost:8080/api/v1
```

### Configuration 
Using above run command app is running with default configuration from `emulator-api/src/main/resources/application
.properties` file: 
```
#emulator socket connection properties
socket.emulator.address=127.0.0.1
socket.emulator.port=5554

#avd auth
avd.auth.token=avd_token_here
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

### Connect to Android Virtual Device
To start working with AVD you need to make 2 steps:
1. Connect to AVD socket.
2. Authenticate to AVD.

#### Step 1

Application will attempt connect to AVD on startup on the address and port form configuration. In case connection 
failure make sure AVD is running and try to connect using Emulator Client service API method `emulator_client/connect`
 or 
`emulator_client/connect/{port}/{address}` if you want to pass different address and port.

#### Step 2

Invoke Authentication service API method `/auth` to authenticate with avd token from `*.properties file` or 
`/auth?authToken=avd_token_here` to pass the token as query param.

After successful authentication interacting with AVD is possible.
 
## API
-   [Emulator Client](https://github.com/jbielak/emulator-api/tree/master/chapters/emulator-client.md)
-   [Authentication](https://github.com/jbielak/emulator-api/tree/master/chapters/authentication.md)
-   [General Commands](https://github.com/jbielak/emulator-api/tree/master/chapters/general-commands.md)
-   [Crash Emulator](https://github.com/jbielak/emulator-api/tree/master/chapters/crash-emulator.md)

## Built With
-   [Java Development Kit 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html)
-   [Gradle 5.2.1](https://spring.io/guides/gs/gradle/)
-   [Spring Boot 2.1.3.RELEASE](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot/2.1.3.RELEASE)

## Test Libraries
-   [JUnit 5.4.0](https://junit.org/junit5/docs/current/api/)

-   [Mockito JUnit Jupiter 2.24.5](https://bintray.com/mockito/maven/mockito) provides an implementation for JUnit 5 
extensions
