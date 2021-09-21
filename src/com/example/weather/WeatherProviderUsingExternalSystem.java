package com.example.weather;

final class WeatherProviderUsingExternalSystem implements WeatherProvider {
    private final WeatherConnector weatherConnector;

    public WeatherProviderUsingExternalSystem(WeatherConnector weatherConnector) {
        this.weatherConnector = weatherConnector;
    }

    public Weather getWeatherFrom(String location) throws CannotFindWeather {
        try {
            String[] weatherData = weatherConnector.weather(location);

            return new Weather(location, weatherData[0], Double.parseDouble(weatherData[1]));
        } catch (Exception e) {
            throw CannotFindWeather.forLocation(location);
        }
    }
}
