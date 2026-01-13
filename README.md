# Lets-Play Project

A modern Spring Boot REST API for managing products and user authentication with JWT security and MongoDB integration.

## Features

- **User Authentication**: Register, login, and manage user accounts with JWT token-based authentication
- **Product Management**: Create, read, update, and delete products with full CRUD operations
- **Role-Based Access Control**: Admin and user roles with different permission levels
- **Security**: JWT token authentication, CORS configuration, and Spring Security integration
- **MongoDB Integration**: NoSQL database for flexible data storage and management
- **Validation**: Input validation using Jakarta Validation API and Hibernate Validator
- **HTTPS Support**: SSL/TLS enabled for secure communication

## Technology Stack

- **Framework**: Spring Boot 4.0.1
- **Language**: Java 25
- **Build Tool**: Gradle
- **Database**: MongoDB
- **Security**: 
  - Spring Security
  - JWT (JSON Web Tokens) - JJWT 0.11.5
  - HTTPS/SSL
- **Validation**: 
  - Jakarta Validation API 3.1.0
  - Hibernate Validator 8.0.1.Final
- **Testing**: JUnit 5 with Spring Boot Test

## Project Structure

```
src/main/java/lets/play/demo/
├── LetsPlayApplication.java          # Spring Boot main application class
├── Config/                           # Configuration classes
│   ├── CustomCorsConfiguration.java  # CORS configuration
│   ├── JwtConf.java                 # JWT configuration
│   ├── JwtUtils.java                # JWT utilities
│   ├── MongoConfig.java             # MongoDB configuration
│   ├── SecurityConfig.java          # Spring Security configuration
│   └── UserDetailServices.java      # Custom user detail services
├── Controller/                       # REST API endpoints
│   ├── AdminController.java         # Admin operations
│   ├── LoginController.java         # User login endpoint
│   ├── MeController.java            # User profile endpoint
│   ├── ProductsController.java      # Product operations
│   └── RegisterController.java      # User registration endpoint
├── Service/                          # Business logic
│   ├── AdminService.java
│   ├── LoginService.java
│   ├── MeService.java
│   ├── ProductService.java
│   └── RegisterService.java
├── Repository/                       # Data access layer
│   ├── LoginRepo.java
│   ├── ProductRepo.java
│   ├── RegisterRepo.java
│   └── UserRepo.java
├── Entity/                           # JPA entities
│   ├── Product.java
│   └── User.java
├── DTOs/                             # Data Transfer Objects
│   ├── LoginReqDto.java
│   ├── LoginResDto.java
│   ├── MeResDto.java
│   ├── ProductCreationDto.java
│   ├── ProductsListDto.java
│   ├── ProductUpdateDto.java
│   ├── RegisterReqDto.java
│   ├── RegisterResDto.java
│   └── UsersListDto.java
└── Exceptions/                       # Custom exception classes
    ├── EmailAlreadyExistException.java
    ├── EmailNotFoundException.java
    ├── GlobalExceptions.java
    ├── InvalidePasswordException.java
    └── InvalidProductException.java
```

## Prerequisites

- Java 25 or higher
- Gradle 8.x or higher
- MongoDB 4.0 or higher
- Docker and Docker Compose (optional, for containerized setup)

## Installation

### 1. Clone the Repository
```bash
git clone https://github.com/Abdelilah-99/lets-play.git
cd lets-play
```

### 2. Configure MongoDB

Update `src/main/resources/application.properties` with your MongoDB credentials:

```properties
spring.data.mongodb.username=admin
spring.data.mongodb.password=123123
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=mongodb
spring.data.mongodb.authentication-database=admin
```

### 3. Build the Project

```bash
# Using gradle wrapper
./gradlew build

# Or using gradle command
gradle build
```

### 4. Run the Application

```bash
# Using gradle wrapper
./gradlew bootRun

# Or using gradle command
gradle bootRun

# Or run the generated JAR
java -jar build/libs/lets-play-0.0.1-SNAPSHOT.jar
```

The application will start on `https://localhost:8443`

## Docker Setup (Optional)

A Docker Compose configuration is provided to run MongoDB:

```bash
docker-compose up -d
```

This will start a MongoDB container with the credentials specified in the configuration.

## API Endpoints

### Authentication
- **POST** `/register` - Register a new user
- **POST** `/login` - Login and receive JWT token

### User Profile
- **GET** `/me` - Get current user profile

### Products
- **GET** `/products` - List all products
- **POST** `/products` - Create a new product (Admin only)
- **PUT** `/products/{id}` - Update a product (Admin only)
- **DELETE** `/products/{id}` - Delete a product (Admin only)

### Admin
- **GET** `/admin/users` - List all users (Admin only)
- **Other admin operations** - See AdminController for details

## Security Configuration

### JWT Token
- **Secret Key**: Configured in `application.properties`
- **Expiration Time**: 3600000 ms (1 hour)
- Token must be included in the `Authorization` header as `Bearer <token>`

### HTTPS/SSL
- **Keystore**: `classpath:keystore.p12`
- **Keystore Password**: `test123`
- **Port**: 8443

### CORS
Custom CORS configuration is implemented in `CustomCorsConfiguration.java` to allow cross-origin requests.

## Configuration Files

### application.properties
Contains database, security, and server configurations:
- MongoDB connection settings
- JWT secret key and expiration time
- HTTPS/SSL settings
- Logging configuration

### build.gradle
Gradle build configuration with all project dependencies

### docker-compose.yml
MongoDB container configuration for local development

## Development

### Running Tests
```bash
./gradlew test
```

### Code Quality
The project uses:
- Jakarta Validation for input validation
- Custom exception handling with global exception controller
- CORS configuration for secure cross-origin requests

## Exception Handling

The application implements custom exception handling for:
- `EmailAlreadyExistException` - User email already registered
- `EmailNotFoundException` - User email not found
- `InvalidePasswordException` - Incorrect password
- `InvalidProductException` - Invalid product operation
- Global exception handler for consistent error responses

## Contributing

1. Create a feature branch (`git checkout -b feature/AmazingFeature`)
2. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
3. Push to the branch (`git push origin feature/AmazingFeature`)
4. Open a Pull Request

## License

This project is proprietary and confidential.

## Support

For issues or questions, please contact the development team.

## Notes

- Ensure MongoDB is running before starting the application
- Default JWT expiration is set to 1 hour
- HTTPS is enabled by default; adjust if needed for development
- All passwords should be changed in production environments
