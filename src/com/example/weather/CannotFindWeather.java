package com.example.weather;

public class CannotFindWeather extends RuntimeException {
    private CannotFindWeather(String location) {
        super("Cannot find weather for location " + location);
    }

    public static CannotFindWeather forLocation(String location) {
        return new CannotFindWeather(location);
    }
}
