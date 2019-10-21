# Service Skeleton project documentation 
 
## Install and Run
### Requirements
1. Docker/Docker Compose
2. maven
3. JDK 1.8
4. Git client

### How to init your service
- Clone repository
- Rename core folder to any desired name of the service
- CD to your project folder
- Run cli command to rename your service
```
> bash service_init.sh -a {service_artifact} -n {Service name}
```
Example:
```
> bash service_init.sh -a "shopupdate" -n "Shop Update"
```

### How to Run?
Current setup provides you two ways to run the application

**Development mode**
- Open the project in your favourite IDE (Ex. IntelliJ IDEA).
- Run docker container for MySQL database
```
 docker-compose -f ./docker/docker-compose-dev.yml up -d
```
- Run project from your IDE

Application resources available:
 - Host URL: [http://localhost:8080](http://localhost:8080)
 - Swagger API Docs:  [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
 
 
**Production mode**
 - Create application JAR by running
```
mvn clean install 
```
- Run docker containers fot Mysql and the Application:
```
docker-compose -f ./docker/docker-compose-prod.yml up --build -d
```
Application resources available:
  - Host URL: [http://localhost:9001](http://localhost:9001)
  - Swagger API Docs:  [http://localhost:9001/swagger-ui.html](http://localhost:9001/swagger-ui.html)
 
