FROM openjdk:17
EXPOSE 8080
ADD target/*.jar findcourse.jar
ENTRYPOINT ["java","-jar","/findcourse.jar"]
