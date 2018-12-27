# Scalable web

Project purpose is to determine difference between two JSON Base64 encoded data which are posted via two endpoints.

## Getting Started

This project have requirements:

● Provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints

<host>/v1/diff/{ID}/left and <host>/v1/diff/{ID}/right

● The provided data needs to be diff-ed and the results shall be available on a third end point

<host>/v1/diff/{ID}

● The results shall provide the following info in JSON format

    o If equal return that
    o If not of equal size just return that
    o If of same size provide insight in where the diffs are, actual diffs are not needed.
        § So mainly offsets + length in the data

● Make assumptions in the implementation explicit, choices are good but need to be
  communicated 

### Prerequisites

You can run the application from the command line with Maven. Or you can build a single executable JAR file that contains all the necessary dependencies, classes, and resources, and run that. This makes it easy to ship, version, and deploy the service as an application throughout the development lifecycle, across different environments, and so forth.

Also, WAR file can be generated to be deployed inside the containers. spring-boot-maven-plugin supports as well. But 
additional configuration may required for embedded container dependencies.

### Installing

You can run the application from the command line with Maven. In the project directory which contains pom.xml file, You 
can run the application using 

```HTML
mvn spring-boot:run
```

Or you can build the JAR file with 

```HTML
mvn clean package 
```

Then you can run the JAR file:

```HTML
java -jar target/scalable-web-0.0.1.jar
```


If you want to use as WAR file, you need to change packaging tag as war in the pom.xml;

```HTML
<packaging>war</packaging>
```

and, again run the application using

```HTML
mvn clean package
```

war file can be seen inside the target directory.

[Postman Client](https://www.getpostman.com/apps) is used for posting and getting tests.

```HTML
localhost:8080/v1/diff/{id}/left
localhost:8080/v1/diff/{id}/right

```

When above URLs post with the data using Postman, data will be stored into Mongo DB with their ids.

To see the comparison result using below link, pair should be posted and inserted into Mongo DB

```HTML
localhost:8080/v1/diff/{id}
```

## Running the tests

Test classes can be found under src/test/java/com.onurtokat.scalableweb/ directory.

```HTML
/config/MongoDbSpringIntegrationTest.java
/controllers/AppControllerTest.java
/repositories/DiffDataRepositoryTest.java
/services/DiffDataServiceImplTest.java
/utils/JsonDecoderTest.java
/utils/OffsetCalculatorTest.java
/utils/PrettyJsonFormatterTest.java
/SmokeTest.java
```
### Break down into end to end tests

<li>MongoDB configuration correctness have been checked</li>
<li>GET and POST mapping method returns have been checked</li>
<li>Repository data correctness have been checked</li>
<li>Exceptions have been checked</li>
<li>Simulated controller</li>
<li>SpringBoot application checked with mvc</li>  

## Deployment

 This project can be ran as JAR in the;
 
 <li>Locally for testing</li>
 <li>On a server</li>
 <li>On a server hosted by cloud provider</li>
 <li>In a container</li>
 <li>In a container hosted by cloud provider</li>

## Built With

* [Spring Boot 2.0.0.M7](http://spring.io/guides) - The Spring Boot framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Embedded MongoDB 1.50.5](https://www.mongodb.com/) - Used to generate Embedded Database
* [Postman Client](https://www.getpostman.com/apps) - Used to test for Posting and Getting endpoints
 

## Author

Onur Tokat

##Suggestion for improvement

● Considering many requests may access to service architecture, and data content of these request would be stored on database management system, then distributed storing and computing need to be set. This requirement drives to horizontal scalability competence.

● Data management platform should be selected according to necessity. Besides RDBMS, NoSQL or Graph Databases can be used for effeciency.