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

### Hotel Controller (`/api/v1/hotel`)

*   **`GET /all`**
    *   **Description:** Retrieves a list of all hotels.
    *   **Request DTO:** None
    *   **Response DTO:** `ResponseDto<List<HotelResponseDto>>`
        *   `HotelResponseDto`: Contains `id`, `hotelName`, `facilities` (List of `Facilities` enum), `reviews` (List of `Reviews` model), `rooms` (List of `Room` model), `address`.

*   **`GET /{id}`**
    *   **Description:** Retrieves a single hotel by its ID.
    *   **Request DTO:** None (ID is a path variable)
    *   **Response DTO:** `ResponseDto<HotelResponseDto>`
        *   `HotelResponseDto`: Contains `id`, `hotelName`, `facilities` (List of `Facilities` enum), `reviews` (List of `Reviews` model), `rooms` (List of `Room` model), `address`.

*   **`POST /create`**
    *   **Description:** Creates a new hotel.
    *   **Request DTO:** `HotelRequestDto`
        *   `HotelRequestDto`: Contains `hotelName` (String), `facilities` (List of `Facilities` enum), `hotelAddress` (String).
    *   **Response DTO:** `ResponseDto<HotelResponseDto>`
        *   `HotelResponseDto`: Contains `id`, `hotelName`, `facilities` (List of `Facilities` enum), `reviews` (List of `Reviews` model), `rooms` (List of `Room` model), `address`.

*   **`PATCH /update/{id}`**
    *   **Description:** Updates an existing hotel by its ID.
    *   **Request DTO:** `HotelRequestDto`
        *   `HotelRequestDto`: Contains `hotelName` (String), `facilities` (List of `Facilities` enum), `hotelAddress` (String).
    *   **Response DTO:** `ResponseDto<HotelResponseDto>`
        *   `HotelResponseDto`: Contains `id`, `hotelName`, `facilities` (List of `Facilities` enum), `reviews` (List of `Reviews` model), `rooms` (List of `Room` model), `address`.

*   **`DELETE /delete/{id}`**
    *   **Description:** Deletes a hotel by its ID.
    *   **Request DTO:** None (ID is a path variable)
    *   **Response DTO:** `ResponseDto<String>` (Returns a success message)

### Package Controller (`/api/v1/package`)

*   **`GET /all`**
    *   **Description:** Retrieves a list of all packages.
    *   **Request DTO:** None
    *   **Response DTO:** `ResponseDto<List<PackageResponseDto>>`
        *   `PackageResponseDto`: Contains `packageId`, `packageName`, `roomId`, `price` (BigDecimal), `tax` (BigDecimal), `occupancy` (Integer), `boardBasis` (BoardBasis enum).

*   **`GET /{id}`**
    *   **Description:** Retrieves a single package by its ID.
    *   **Request DTO:** None (ID is a path variable)
    *   **Response DTO:** `ResponseDto<PackageResponseDto>`
        *   `PackageResponseDto`: Contains `packageId`, `packageName`, `roomId`, `price` (BigDecimal), `tax` (BigDecimal), `occupancy` (Integer), `boardBasis` (BoardBasis enum).

*   **`POST /create`**
    *   **Description:** Creates a new package.
    *   **Request DTO:** `PackageRequestDto`
        *   `PackageRequestDto`: Contains `packageName` (String), `roomId` (String), `price` (BigDecimal), `tax` (BigDecimal), `occupancy` (Integer), `boardBasis` (BoardBasis enum).
    *   **Response DTO:** `ResponseDto<PackageResponseDto>`
        *   `PackageResponseDto`: Contains `packageId`, `packageName`, `roomId`, `price` (BigDecimal), `tax` (BigDecimal), `occupancy` (Integer), `boardBasis` (BoardBasis enum).

*   **`PATCH /update/{id}`**
    *   **Description:** Updates an existing package by its ID.
    *   **Request DTO:** `PackageUpdateRequestDto`
        *   `PackageUpdateRequestDto`: Contains `packageName` (String), `price` (BigDecimal), `tax` (BigDecimal), `occupancy` (Integer), `boardBasis` (BoardBasis enum).
    *   **Response DTO:** `ResponseDto<PackageResponseDto>`
        *   `PackageResponseDto`: Contains `packageId`, `packageName`, `roomId`, `price` (BigDecimal), `tax` (BigDecimal), `occupancy` (Integer), `boardBasis` (BoardBasis enum).

*   **`DELETE /delete/{id}`**
    *   **Description:** Deletes a package by its ID.
    *   **Request DTO:** None (ID is a path variable)
    *   **Response DTO:** `ResponseDto<String>` (Returns a success message)

### Room Controller (`/api/v1/room`)

*   **`POST /create`**
    *   **Description:** Creates a new room.
    *   **Request DTO:** `RoomRequestDto`
        *   `RoomRequestDto`: Contains `roomName` (String), `amenities` (List of `Amenities` enum), `hotelId` (String).
    *   **Response DTO:** `ResponseDto<RoomResponseDto>`
        *   `RoomResponseDto`: Contains `id`, `roomName`, `amenities` (List of `Amenities` enum), `hotelId`, `packageIds` (List of String).

*   **`PATCH /update/{id}`**
    *   **Description:** Updates an existing room by its ID.
    *   **Request DTO:** `RoomUpdateRequestDto`
        *   `RoomUpdateRequestDto`: Contains `roomName` (String), `amenities` (List of `Amenities` enum).
    *   **Response DTO:** `ResponseDto<RoomResponseDto>`
        *   `RoomResponseDto`: Contains `id`, `roomName`, `amenities` (List of `Amenities` enum), `hotelId`, `packageIds` (List of String).

*   **`PATCH /update/availabilities/{id}`**
    *   **Description:** Updates the availability of a room by its ID for specific dates.
    *   **Request DTO:** `List<RoomAvailabilityRequestDto>`
        *   `RoomAvailabilityRequestDto`: Contains `date` (LocalDate), `status` (RoomStatus enum).
    *   **Response DTO:** `ResponseDto<String>` (Returns a success message)

*   **`DELETE /delete/{id}`**
    *   **Description:** Deletes a room by its ID.
    *   **Request DTO:** None (ID is a path variable)
    *   **Response DTO:** `ResponseDto<String>` (Returns a success message)

*   **`GET /all`**
    *   **Description:** Retrieves a list of all rooms.
    *   **Request DTO:** None
    *   **Response DTO:** `ResponseDto<List<RoomResponseDto>>`
        *   `RoomResponseDto`: Contains `id`, `roomName`, `amenities` (List of `Amenities` enum), `hotelId`, `packageIds` (List of String).

### Common DTOs

*   **`ResponseDto<T>`**
    *   **Description:** A generic response DTO used for all API responses, encapsulating the status code, status message, and the actual data.
    *   **Fields:** `statusCode` (String), `statusMsg` (String), `data` (Generic type `T`).

*   **`ErrorResponseDto`**
    *   **Description:** DTO for conveying error information in a standardized format.
    *   **Fields:** `apiPath` (String), `errorCode` (HttpStatus), `errorMessage` (String), `errorTime` (LocalDateTime).

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