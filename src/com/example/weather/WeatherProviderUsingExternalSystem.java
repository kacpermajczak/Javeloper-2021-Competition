package com.example.weather;

final class WeatherProviderUsingExternalSystem implements WeatherProvider {
    private final WeatherConnector weatherConnector;

    public WeatherProviderUsingExternalSystem(WeatherConnector weatherConnector) {
        this.weatherConnector = weatherConnector;
    }

    public Weather getWeatherFrom(String location) {
        try {
            String[] weatherData = weatherConnector.weather(location);

            return new Weather(location, weatherData[0], Double.parseDouble(weatherData[1]));
        } catch (Exception e) {
            log(e);
            return null;
        }
    }

    private static void log(Object object) {
        System.out.println("Weather=" + object.toString());
    }

    private static void log(String text) {
        System.out.println("Weather=" + text);
    }
}
