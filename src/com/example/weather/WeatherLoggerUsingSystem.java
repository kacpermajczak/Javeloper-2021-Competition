package com.example.weather;

final class WeatherLoggerUsingSystem implements WeatherLogger {
    public void log(Object object) {
        System.out.println("Weather=" + object.toString());
    }

    public void log(String text) {
        System.out.println("Weather=" + text);
    }
}
