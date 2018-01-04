
# Bookstore (back-end) application
- __Maven build lifecycle:__ Maven archetype to bootstrap the basic directory structure and manage Java EE dependencies (configuration file pom.xml). 
- __The Domain model:__ Implementing the _Book entity_ using Java Persistance API to connect to Relational Database by map entity.
- __Entity management:__ Using Java Transaction API to implement CRUD operations (Create, Read, Update, Delete) with Java Persistence Query Language (Transaction demarcation).
- __Testing:__ Using JUnit to test in isolation part of the code and Arquillian integration tests to test integrated systems in a system container.
- __Validation:__ Using constraints on Object attributes and on Object method parameters to manage data by the system container.
- __Dependency injection:__ Java bean is a managed Java EE class that benefits from servives given by the system container. Using injection annotation in order to better manage Java beans.
- __REST API:__ Exposing REST (Representational state transfer) API using annotations in order to consume it with a client API. So that an HTTP endpoint to be an entry point for Angular.
- __API documentation:__ Using Maven to automate the process of generating JSon contract files for documenting API and to visualize contracts with Swagger UI (User Interface).
