##  Report Document - [Link](https://drive.google.com/file/d/1vG4lkt_D6ykmDmnWxjngEVQnx_UTq651/view?usp=sharing) 

# Weather REST Service

A simple RESTful web service for managing weather information using Spring Boot.

## Features

- Retrieve weather information for specific cities
- Get a list of all available cities with their weather data
- Add new weather data for cities
- Update existing weather data
- Delete weather data for a city

## Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- Internet connection (for downloading dependencies)

## Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/webservice-REST-implementation.git
   cd webservice-REST-implementation
   ```

2. **Build the application**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`.

## API Endpoints

### Get All Weather Data
- **URL**: `GET /api/weather`
- **Response**: Returns a map of all cities and their weather data

### Get Weather by City
- **URL**: `GET /api/weather/{city}`
- **Parameters**: 
  - `city` (path, required): Name of the city
- **Response**: Weather data for the specified city
- **Error**: 404 if city not found

### Add Weather Data
- **URL**: `POST /api/weather`
- **Headers**: 
  - `Content-Type: application/json`
- **Request Body**: 
  ```json
  {
    "city": "City Name",
    "description": "Weather description",
    "temperatureCelsius": 25.0
  }
  ```
- **Response**: Created weather data with HTTP 201
- **Error**: 400 if city already exists

### Update Weather Data
- **URL**: `PUT /api/weather/{city}`
- **Headers**: 
  - `Content-Type: application/json`
- **Request Body**: 
  ```json
  {
    "description": "Updated weather description",
    "temperatureCelsius": 26.5
  }
  ```
- **Response**: Updated weather data
- **Error**: 404 if city not found

### Delete Weather Data
- **URL**: `DELETE /api/weather/{city}`
- **Parameters**: 
  - `city` (path, required): Name of the city to delete
- **Response**: Success message with HTTP 200
- **Error**: 404 if city not found

## Examples

### Get all weather data
```bash
curl http://localhost:8080/api/weather
```

### Get weather for a specific city
```bash
curl http://localhost:8080/api/weather/Addis%20Ababa
```

### Add new weather data
```bash
curl -X POST -H "Content-Type: application/json" \
  -d '{"city":"Gondar","description":"Sunny","temperatureCelsius":28.0}' \
  http://localhost:8080/api/weather
```

### Update weather data
```bash
curl -X PUT -H "Content-Type: application/json" \
  -d '{"description":"Mostly Sunny","temperatureCelsius":29.0}' \
  http://localhost:8080/api/weather/Gondar
```

### Delete weather data
```bash
curl -X DELETE http://localhost:8080/api/weather/Gondar
```

## Built With
- [Maven](https://maven.apache.org/) 
