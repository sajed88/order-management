# order-management
a.	How to build the application.







b.	How to create and run the docker image.

1) create Dockerfile in the root of project.

2) add tag finalName in pom.xml name is spring-boot-docker

3) add this code in Dockerfile :
    FROM openjdk:18
   
    EXPOSE 8080
   
    ADD target/spring-boot-docker.jar spring-boot-docker.jar
   
    ENTRYPOINT ["java", "-jar", "/spring-boot-docker.jar"]
   
   
5)write in terminal mvn clean

6)write in terminal mvn install

7)create docker image write in terminal docker build -t order-management .

8)run docker docker write in terminal run -p 8080:8080 order-management java -jar order-management.jar
