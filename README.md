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
GET http://localhost:8100/competition-team <br /> 
GET http://localhost:8100/competition-by-team/{id} <br /> 
GET http://localhost:8100/team-by-competition/{id} <br /> 
POST http://localhost:8100/team/{id}/competition{id} <br /> 
DELETE http://localhost:8100/team/{id}/competition{id} <br /> 
 <br /> 
-League Service <br /> 
GET http://localhost:8100/leagues <br /> 
GET http://localhost:8100/leagues/{id} <br /> 
POST http://localhost:8100/leagues <br /> 
DELETE http://localhost:1000/leagues/{id} <br /> 
 <br /> 
-Competition Service <br /> 
GET http://localhost:8100/competitions <br /> 
GET http://localhost:8100/competitions/{id} <br /> 
POST http://localhost:8100/competitions <br /> 
DELETE http://localhost:1000/competitions/{id} <br /> 
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



