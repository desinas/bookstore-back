
# Bookstore (back-end) application
- __Maven build lifecycle:__ Maven archetype to bootstrap the basic directory structure and manage Java EE dependencies (configuration file pom.xml). 
- __The Domain model:__ Implementing the _Book entity_ using Java Persistance API to connect to Relational Database by map entity.
- __Entity management:__ Using Java Transaction API to implement CRUD operations (Create, Read, Update, Delete) with Java Persistence Query Language (Transaction demarcation).
- __Testing:__ Using JUnit to test in isolation part of the code and Arquillian integration tests to test integrated systems in a system container.
- __Validation:__ Using constraints on Object attributes and on Object method parameters to manage data by the system container.
- __Dependency injection:__ Java bean is a managed Java EE class that benefits from servives given by the system container. Using injection annotation in order to better manage Java beans.
- __REST API:__ Exposing REST (Representational state transfer) API using annotations in order to consume it with a client API. So that an HTTP endpoint to be an entry point for Angular.
- __API documentation:__ Using Maven to automate the process of generating JSon contract files for documenting API and to visualize contracts with Swagger UI (User Interface).

## Defining the Domain Model

The BookStore web application deals with books. Books are the basic domain model of the application and need to be mapped and then store into a relational database.
* In the `com.pluralsight.bookstore.model` package create a `Book` entity with an id (the primary key), a title, a description, a unitCost, an isbn, a publicationDate, nbOfPages, imagURL and a language.
* In the `com.pluralsight.bookstore.model` package create a `Language` enumeration.
* In the `src/main/resources/META-INF` directory create a `persistence.xml` file for the `bookStorePU` persistence unit.

*To execute the application you have to build it (`mvn clean package`) and then deploy the `bookstore-back.war` into [WildFly](https://wildfly.org).*

## Adding a Transactional Repository

Mapping an entity to a database is not enough. We need to add a transactional repository to be able to persist, find or remove data from the relational database.
* Add two named queries (`FIND_ALL` and `COUNT_ALL`) to the `Book` entity
* In the `com.pluralsight.bookstore.repository` package create a `BookRepository` transactional repository with methods create, find, delete, findAll and countAll.

*To execute the application you have to build it (`mvn clean package`) and then deploy the `bookstore-back.war` into [WildFly](https://wildfly.org).*

## Testing the Java EE Application

This module uses JUnit and Arquillian frameworks to test the first components of our BookStore application
* In the `src/test/java` directory create a `com.pluralsight.bookstore.repository` package with a `BookRepositoryTest` test class. This should test the repository with methods such as shouldGetNoBook, shouldCreateABook, shouldFindTheCreatedBook, shouldGetOneBook, shouldDeleteTheCreatedBook, shouldGetNoMoreBook
* Add the `src/test/resources/META-INF/test-persistence.xml` file for test purpose
* Add the `src/test/resources/arquillian.xml` file for Arquillian test with the `arquillian-wildfly-remote` qualifier
* Startup WildFly and run `mvn clean test` so the Arquillian tests pass

*To execute the application you have to build it (`mvn clean package`) and then deploy the `bookstore-back.war` into [WildFly](https://wildfly.org).*

## Validating Data

To make sure the data of a book is valid, this module uses Bean Validation to add constraints to the Book model and the transactional repository.
* In the `Book` entity add constraints on the attributes title, description, unitCost, isbn and publicationDate
* In the `BookRepository` add constraints to the parameter of the methods find and delete
* In the `BookRepositoryTest` add extra methods to check the constraints are working shouldFailCreatingABookWithNullTitle, shouldFailCreatingABookWithLowUnitCostTitle, shouldFailCreatingABookWithNullISBN, shouldFailInvokingFindByIdWithNull and shouldFailInvokingDeleteWithNull
* Startup WildFly and run `mvn clean test` so the Arquillian tests pass

*To execute the application you have to build it (`mvn clean package`) and then deploy the `bookstore-back.war` into [WildFly](https://wildfly.org).*

## Injecting Beans

In object oriented programming, objects depend on others. Thanks to injection we can easily ask the container to provide the needed dependencies.
* In the package `com.pluralsight.bookstore.util` create the new bean `IsbnGenerator` with a `generateNumber` returning a random ISBN number
* The `BookRepository` uses injection to get a reference of the `IsbnGenerator` bean that it uses in the `create` method
* For injection to work, create an empty `src/main/webapp/WEB-INF/beans.xml` file
* Adjust the Arquillian tests so it can test injection

*To execute the application you have to build it (`mvn clean package`) and then deploy the `bookstore-back.war` into [WildFly](https://wildfly.org).*

## Exposing a REST Service

This module adds a REST API in front of the book repository to allow HTTP calls to interact with the back-end with JSon format.
* In the `com.pluralsight.bookstore.rest` package create the `JAXRSConfiguration` class to configure the REST api to the `api` web root
* In the `com.pluralsight.bookstore.rest` package create the `BookEndpoint` and define the methods createBook (POST), getBook (GET), deleteBook (DELETE), getBooks (GET) and countBooks (GET)
* In the directory `src/main/resources` create an `import.sql` to add data to the database (make sure to add a `sql-load-script-source` property in the `persistence.xml` file

*To execute the application you have to build it (`mvn clean package`) and then deploy the `bookstore-back.war` into [WildFly](https://wildfly.org).*

## Documenting the REST Service

To help us developing the Angular front-end, we need to document our REST API. This module uses Swagger to generate documentation of our REST endpoint.
* In the `BookEndpoint` add Swagger annotations to document all the exposed methods

*To execute the application you have to build it (`mvn clean package`) and then deploy the `bookstore-back.war` into [WildFly](https://wildfly.org).*


# Structure 

The BookStore application is divided into a Java EE REST back-end (`bookstore-back`) and an Angular front-end (`bookstore-front`).


## Bookstore Back 

The code of this module follows the [Maven](http://maven.apache.org/) directory structure. The `src/main/` directory contains the main source code while you will find the test class under `src/test/`. The `pom.xml` file is Maven specific and it describes the project and its dependencies.

Once [Maven](http://maven.apache.org/) and a [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/index.html) are installed, enter the following Maven commands:

* `mvn help:help`       : shows Maven help
* `mvn clean`           : cleans the `target` directory
* `mvn compile`         : compiles the code
* `mvn test`            : runs the test case (you need WildFly to be up and running)
* `mvn package`         : packages the code into a war file
* `mvn clean package`   : executes a clean and then a package

Once [Wildfly](http://wildfly.org/) is installed, deploy the war file and go to [http://localhost:8080/bookstore-back/]()




