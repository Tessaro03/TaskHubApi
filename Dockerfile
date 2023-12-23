FROM openjdk:20
ADD ./docker-spring-boot.jar docker-spring-boot.jar
ENTRYPOINT ["Java", "-jar", "docker-spring-boot.jar"]
