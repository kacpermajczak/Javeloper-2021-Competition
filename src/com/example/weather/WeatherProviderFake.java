package com.example.weather;

public class WeatherProviderFake implements WeatherProvider{
    @Override
    public Weather getWeatherFrom(String location) {
        return new Weather(location, "some date", 22.5);
    }
}
