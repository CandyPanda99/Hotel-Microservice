
# Hotel Microservice

This is a Spring Boot microservice for managing hotels, rooms, and packages. It provides a RESTful API for performing CRUD operations on these resources.

## Features

- **Hotel Management:** Create, read, update, and delete hotels.
- **Room Management:** Create, read, update, and delete rooms.
- **Package Management:** Create, read, update, and delete packages.
- **Room Availability:** Manage room availability.
- **Service Discovery:** Registers with Eureka for service discovery.
- **Centralized Configuration:** Uses Spring Cloud Config for centralized configuration.
- **Database:** Uses PostgreSQL as the database.
- **API Documentation:** Uses Springdoc OpenAPI for API documentation.

## Getting Started

### Prerequisites

- Java 23
- Maven
- PostgreSQL

### Building the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/hotel-microservice.git
   ```
2. Navigate to the project directory:
   ```bash
   cd hotel-microservice
   ```
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

### Running the Project

1. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
2. The application will be available at `http://localhost:8082`.

## API Endpoints

The following are the main API endpoints:

- `POST /api/v1/hotel/create`: Creates a new hotel.
- `GET /api/v1/hotel/all`: Retrieves all hotels.
- `GET /api/v1/hotel/{id}`: Retrieves a hotel by its ID.
- `PATCH /api/v1/hotel/update/{id}`: Updates a hotel.
- `DELETE /api/v1/hotel/delete/{id}`: Deletes a hotel.
- `POST /api/v1/room/create`: Creates a new room.
- `GET /api/v1/room/all`: Retrieves all rooms.
- `PATCH /api/v1/room/update/{id}`: Updates a room.
- `DELETE /api/v1/room/delete/{id}`: Deletes a room.
- `PATCH /api/v1/room/update/availabilities/{id}`: Updates room availabilities.
- `POST /api/v1/package/create`: Creates a new package.
- `GET /api/v1/package/all`: Retrieves all packages.
- `GET /api/v1/package/{id}`: Retrieves a package by its ID.
- `PATCH /api/v1/package/update/{id}`: Updates a package.
- `DELETE /api/v1/package/delete/{id}`: Deletes a package.

For more details on the API, you can access the Swagger UI at `http://localhost:8082/swagger-ui.html`.

## Technologies Used

- **Spring Boot:** Framework for creating stand-alone, production-grade Spring-based Applications.
- **Spring Cloud:** Tools for building distributed systems.
- **Spring Data JPA:** Persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
- **Spring Web:** Build web, including RESTful, applications using Spring MVC.
- **Spring Actuator:** Adds production-ready features to your application.
- **Eureka:** Service discovery.
- **Spring Cloud Config:** Centralized configuration.
- **PostgreSQL:** Open source object-relational database system.
- **MapStruct:** Java bean mapper.
- **Lombok:** Java library that automatically plugs into your editor and build tools to spice up your java.
- **Springdoc OpenAPI:** Library for generating OpenAPI 3 documentation for Spring Boot projects.

## Docker

### Building the Docker Image

To build the Docker image, run the following command:

```bash
docker build --platform=linux/amd64 . -t lasalhettiarachchi/hotelms:s3
```

### Running the Docker Container

To run the Docker container, use the following command:

```bash
docker run -p 8082:8082 lasalhettiarachchi/hotelms:s3
```

The application will be available at `http://localhost:8082`.

### Pushing the Docker Image

To push the Docker image to Docker Hub, use the following command:

```bash
docker push docker.io/lasalhettiarachchi/hotelms:s3
```
