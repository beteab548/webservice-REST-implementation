package org.example.service;

import org.example.model.Weather;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherRESTService {

    private final Map<String, Weather> weatherData = new HashMap<>();

    public WeatherRESTService() {
        // Initialize with sample data
        weatherData.put("Addis Ababa", new Weather("Addis Ababa", "Sunny", 21));
        weatherData.put("Bahir Dar", new Weather("Bahir Dar", "Partly Cloudy", 24));
        weatherData.put("Hawassa", new Weather("Hawassa", "Rainy", 22));
        System.out.println("Weather REST Service initialized with cities: " + weatherData.keySet());
    }

    // GET - Get weather by city name
    @GetMapping("/{city}")
    public ResponseEntity<?> getWeather(@PathVariable String city) {
        System.out.println("GET request for city: " + city);
        Weather weather = weatherData.get(city);
        if (weather == null) {
            return ResponseEntity.status(404)
                    .body(Map.of("error", "Weather data not found for city: " + city));
        }
        return ResponseEntity.ok(weather);
    }

    // GET - Get all cities weather data
    @GetMapping
    public ResponseEntity<Map<String, Weather>> getAllWeather() {
        System.out.println("GET all weather data");
        return ResponseEntity.ok(weatherData);
    }

    // POST - Add new city weather data
    @PostMapping
    public ResponseEntity<?> addWeather(@RequestBody Weather weather) {
        System.out.println("POST request to add: " + weather);
        if (weather.getCity() == null || weather.getCity().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "City name is required"));
        }
        weatherData.put(weather.getCity(), weather);
        return ResponseEntity.status(201).body(weather);
    }

    // PUT - Update existing city weather data
    @PutMapping("/{city}")
    public ResponseEntity<?> updateWeather(@PathVariable String city, @RequestBody Weather weather) {
        System.out.println("PUT request to update: " + city);
        if (!weatherData.containsKey(city)) {
            return ResponseEntity.status(404)
                    .body(Map.of("error", "City not found: " + city));
        }
        weatherData.put(city, weather);
        return ResponseEntity.ok(weather);
    }

    // DELETE - Remove city weather data
    @DeleteMapping("/{city}")
    public ResponseEntity<?> deleteWeather(@PathVariable String city) {
        System.out.println("DELETE request for city: " + city);
        if (!weatherData.containsKey(city)) {
            return ResponseEntity.status(404)
                    .body(Map.of("error", "City not found: " + city));
        }
        weatherData.remove(city);
        return ResponseEntity.ok(Map.of("message", "Weather data deleted for city: " + city));
    }

    // GET - Convert Celsius to Fahrenheit
    @GetMapping("/convert/to-fahrenheit")
    public ResponseEntity<?> toFahrenheit(@RequestParam double celsius) {
        System.out.println("Temperature conversion request: " + celsius + "°C to Fahrenheit");
        double fahrenheit = (celsius * 9 / 5) + 32;
        return ResponseEntity.ok(Map.of(
                "celsius", celsius,
                "fahrenheit", Math.round(fahrenheit * 100.0) / 100.0
        ));
    }

    // GET - Convert Fahrenheit to Celsius
    @GetMapping("/convert/to-celsius")
    public ResponseEntity<?> toCelsius(@RequestParam double fahrenheit) {
        System.out.println("Temperature conversion request: " + fahrenheit + "°F to Celsius");
        double celsius = (fahrenheit - 32) * 5 / 9;
        return ResponseEntity.ok(Map.of(
                "fahrenheit", fahrenheit,
                "celsius", Math.round(celsius * 100.0) / 100.0
        ));
    }

    // GET - Get list of available cities
    @GetMapping("/cities")
    public ResponseEntity<?> getAvailableCities() {
        System.out.println("GET available cities");
        return ResponseEntity.ok(Map.of(
                "cities", weatherData.keySet(),
                "count", weatherData.size()
        ));
    }

    // GET - Health check endpoint
    @GetMapping("/health")
    public ResponseEntity<?> healthCheck() {
        return ResponseEntity.ok(Map.of(
                "status", "UP",
                "service", "Weather REST Service",
                "citiesCount", weatherData.size()
        ));
    }
}