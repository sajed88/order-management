FROM openjdk:18
EXPOSE 8080
ADD target/spring-boot-docker.jar spring-boot-docker.jar
ENTRYPOINT ["java", "-jar", "/spring-boot-docker.jar"]

#docker build -t order-management .
#docker run -p 8080:8080 order-management java -jar order-management.jar