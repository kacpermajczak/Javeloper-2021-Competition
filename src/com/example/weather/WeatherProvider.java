package com.example.weather;

interface WeatherProvider {
    Weather getWeatherFrom(String location) throws CannotFindWeather;
}
