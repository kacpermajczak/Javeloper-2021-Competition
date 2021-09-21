package com.example.weather;

final class WeatherConnectorUsingExternalSystem implements WeatherConnector {

    @Override
    public String[] weather(String location) {
        //todo - initialize connection with external service
        return new String[]{"todo", "todo"};
    }
}
