FROM openjdk:17-jdk
COPY ./build/libs/Puppicasso-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]