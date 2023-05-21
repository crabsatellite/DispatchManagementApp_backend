# Dispatch Management Application
## Overview
The Dispatch Management Application is a robust Java-based system designed to manage and monitor the flow of deliveries within a logistics operation. It is designed using a microservices architecture and follows a layered approach, with controllers, services, and repositories being the main layers. The system also ensures secure access with a JWT authentication system.

 [Frontend](https://github.com/zilinli0130/dispatch_management_app_frontend)

## Directory Structure
* src/main/java/com/flagteam/app: Main application logic written in Java.
 * controller: Contains the application controllers, including UserController.java and DeliveryController.java.
* service: Contains the service interfaces and their implementation classes.
* repository: Contains the JPA repositories.
* config: Contains configuration classes, such as WebSecurityConfig.java, JwtConfig.java, and DatabaseConfig.java.
* exception: Contains custom exceptions and handlers.
* model: Contains the model classes representing the domain objects.
* Application.java: The main entry point for the application.
* src/main/resources: Contains application configuration files and logging configuration.
* src/main/static: Static files location.
* src/test: Contains test cases for the application.
* target: Generated files by Maven, including compiled source code and build results.
* pom.xml: The Project Object Model (POM) file for Maven.

## How to Run
* Ensure you have Java and Maven installed.
* Clone this repository.
* Navigate to the root directory.
* Run mvn clean install to build the application.
* After the build, run java -jar target/DispatchManagementApp.jar to start the application.

## Contributors
Please feel free to reach out for any issues or contributions on this page. 
