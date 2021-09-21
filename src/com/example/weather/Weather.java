package com.example.weather;

final class Weather {
    private final String location;
    private final String weatherDatum;
    private final double temperature;

    public Weather(final String location, final String weatherDatum, final double temp) {
        this.location = location;
        this.weatherDatum = weatherDatum;
        this.temperature = temp;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "location='" + location + '\'' +
                ", weatherDatum='" + weatherDatum + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}
