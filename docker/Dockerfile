FROM maven:3.6.2-jdk-8

EXPOSE 8080

ADD ./ service/
RUN (cd service/; mvn clean install;)
RUN (cp service/target/*.jar /app.jar;)

WORKDIR /service
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","/app.jar"]