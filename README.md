# JmsHomeworkSDT
A repository for sharing a potential implementation of the JmsHomework for SDT course.
The implementation lacks a single feature - it does not have a durable subscription for the FamilyDoctor.

It is connected to a local instance of a mongodb server. For a successful connection, you neet to have a database called `mongodb` with a collection called `people`. 

If you are lazy and do not know how to configure mongodb for spring you have to add these two lines in `application.properties` 
```
spring.data.mongodb.uri=mongodb://localhost:27017/people
spring.data.mongodb.database=mongodb
```

and these dependencies in pom.xml
```
<dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-mongodb</artifactId>
            <version>3.0.3.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-mongodb</artifactId>
        </dependency>
```

with these changes done, you should have a successful connection. Enjoy 😄
