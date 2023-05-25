# Dispatch Management Application (Backend)

Welcome to the backend repository of the Dispatch Management Application! This project is a robust Java-based SpringBoot system designed to manage and monitor the flow of deliveries within a logistics operation. You can find frontend code [here](https://github.com/zilinli0130/dispatch_management_app_frontend).

## Tech Stack

The backend is developed using Java and Spring Boot, following a microservices architecture and a layered approach. It ensures secure access with a JWT authentication system.

## Backend

The backend of the Dispatch Management Application boasts several features:

- **Microservices Architecture**: The application is designed using a microservices architecture, enhancing scalability and maintainability.

- **Layered Approach**: The application follows a layered approach, with controllers, services, and repositories being the main layers.

- **Security**: The system ensures secure access with a JWT authentication system.

## Directory Structure

- `src/main/java/com/flagteam/app`: Main application logic written in Java.
  - `controller`: Contains the application controllers, including UserController.java and DeliveryController.java.
  - `service`: Contains the service interfaces and their implementation classes.
  - `repository`: Contains the JPA repositories.
  - `config`: Contains configuration classes, such as WebSecurityConfig.java, JwtConfig.java, and DatabaseConfig.java.
  - `exception`: Contains custom exceptions and handlers.
  - `model`: Contains the model classes representing the domain objects.
- `Application.java`: The main entry point for the application.
- `src/main/resources`: Contains application configuration files and logging configuration.
- `src/main/static`: Static files location.
- `src/test`: Contains test cases for the application.
- `target`: Generated files by Maven, including compiled source code and build results.
- `pom.xml`: The Project Object Model (POM) file for Maven.

## Getting Started

1. **Ensure you have Java and Maven installed.**
2. **Clone the Repository**: Clone this repository to your local machine using the command `git clone https://github.com/crabsatellite/DispatchManagementApp_backend.git`.
3. **Navigate to the root directory.**
4. **Build the Application**: Run `mvn clean install` to build the application.
5. **Run the Application**: After the build, run `java -jar target/DispatchManagementApp.jar` to start the application.

## Contributions

This project is still under development. Contributions are very welcome!

## Contact

Feel free to get in touch if you have any questions.

Enjoy managing your deliveries with the Dispatch Management Application!

---
