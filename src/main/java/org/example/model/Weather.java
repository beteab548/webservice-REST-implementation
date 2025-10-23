package org.example.model;

public class Weather {
    private String city;
    private String description;
    private double temperatureCelsius;

    public Weather() { }

    public Weather(String city, String description, double temperatureCelsius) {
        this.city = city;
        this.description = description;
        this.temperatureCelsius = temperatureCelsius;
    }

    // Getters and Setters
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getTemperatureCelsius() { return temperatureCelsius; }
    public void setTemperatureCelsius(double temperatureCelsius) { this.temperatureCelsius = temperatureCelsius; }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", description='" + description + '\'' +
                ", temperatureCelsius=" + temperatureCelsius +
                '}';
    }
}