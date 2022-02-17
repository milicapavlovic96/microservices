# Spring Cloud Microservices Project
The assignment was to make minimum 5 microservices that communicate with each other using Spring Cloud Framewrok.

# Technologies
Application is developed in Spring Cloud Framework (Java Programming Language). For database, i used H2 in-memory database. API is tested with Postman. Docker is used for containerization. All services are started with one command - Docker-compose up.

Other used technologies: <br /> 
-Feign(microservice communication library) <br /> 
-Resillience4j(fault tolerance library, Circuit breaker and Rate limitter) <br /> 
-Eureka naming server(application that holds information about all client service applications) <br /> 
-Spring Cloud Gateway(library for building an API Gateway on top of Spring WebFlux) <br /> 
-Spring Cloud Config(support for externalized configuration in a distributed system) <br /> 
-Zipkin(distributed tracing system) <br /> 
-RabbitMQ(open source message broker) <br /> 
 <br /> 
Also, Git is used for versioning. <br /> 

# Microservices URI's
-Player Service <br /> 
GET http://localhost:8000/players <br /> 
GET http://localhost:8000/players/{id} <br /> 
POST http://localhost:8000/players <br /> 
DELETE http://localhost:8000/players/{id} <br /> 
 <br /> 
-Team Service  <br /> 
GET http://localhost:8100/teams <br /> 
GET http://localhost:8100/teams/{id} <br /> 
POST http://localhost:8100/teams <br /> 
DELETE http://localhost:1000/teams/{id} <br /> 
 <br /> 
-Competition Team Service <br /> 
GET http://localhost:8200/competition-team <br /> 
GET http://localhost:8200/competition-by-team/{id} <br /> 
GET http://localhost:8200/team-by-competition/{id} <br /> 
POST http://localhost:8200/team/{id}/competition{id} <br /> 
DELETE http://localhost:8200/team/{id}/competition{id} <br /> 
 <br /> 
-League Service <br /> 
GET http://localhost:8300/leagues <br /> 
GET http://localhost:8300/leagues/{id} <br /> 
POST http://localhost:8300/leagues <br /> 
DELETE http://localhost:8300/leagues/{id} <br /> 
 <br /> 
-Competition Service <br /> 
GET http://localhost:8400/competitions <br /> 
GET http://localhost:8400/competitions/{id} <br /> 
POST http://localhost:8400/competitions <br /> 
DELETE http://localhost:8400/competitions/{id} <br /> 
 <br /> 
-Eureka Naming Server <br /> 
 http://localhost:8761/eureka <br /> 
-Config Server  <br /> 
http://localhost:8888 <br /> 
-Zipkin <br /> 
http://localhost:9411/zipkin <br /> 
-API Gateway <br /> 
http://localhost:8765/{service-name}/{route} <br /> 
Example: http://localhost:8765/player-service/players <br /> 
 <br /> 
# Diagram 

 ![Alt text](https://fv9-4.failiem.lv/thumb_show.php?i=mqb7bhgta&view "Title")
 
 # Description
 Player can play in one team and one team can have multiple players. (one-to-many) <br /> 
 Team can be part of exactly one league and one league can have several teams. (one-to-many) <br /> 
 One team can participate in several competitions and there is always several teams in one competition. (many-to-many)
 # Launching
Open Command Prompt and navigate into the directory where project is (docker-compose.yaml must be present). Start up the Docker Engine, and run docker-compose up in Command Prompt.
# Testing
Eureka naming server's dashboard shows all the services that are up and running. <br /> 
Zipkin server provides distributed tracing and can also create dependency graph after a few requests have been fired. <br /> 
The collection of requests for Postman is provided in the Spring Cloud Microservices.postman_collection.json file.
Postman's runner tool can also be used to test for rate limiting feature.  <br /> 
