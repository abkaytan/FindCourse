FROM openjdk:17
EXPOSE 8080
ADD target/*jar deneme.jar
ENTRYPOINT ["java", "-jar", "/deneme.jar"]
