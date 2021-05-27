#Welcome to Zoo Design
```
 1. ZooConfig.java will help to bootstrap Zoo with Areas, Pens and Default Animals
 2. Using h2 Database
 3. Postman collection is also present in the codebase for testing locally
 ```
#Database configuration
```spring.datasource.url=jdbc:h2:mem:zoodb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.h2.console.enabled=true

zoo.areas.count=4
zoo.area.pen.count=16
initialize.zoo.default.data=true (If this flag is set to false it wont bootstrap the data while starting)
```      
#Post setup whats next
```
API to update area with pens
    PATCH http://localhost:8080/zoo/updateArea
1. New animal can be added via API 
    POST http://localhost:8080/animal/addAnimals
2. API to fetch All Animals in ZOO
    GET http://localhost:8080/zoo/fetchAllAnimals
3. API to fetch All Pens in Zoo
    GET http://localhost:8080/zoo/availablePens
4. API to assign Animal to Pen 
    POST http://localhost:8080/animal/assignAnimalToPen?animalId=1&penId=1
5. API to fetch Total count of Animals in Zoo
    GET http://localhost:8080/animal/count
```
