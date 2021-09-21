package com.example.weather;

public class WeatherLoggerSpy implements WeatherLogger {

    int counter = 0;

    @Override
    public void log(Object object) {
        this.counter++;
    }

    @Override
    public void log(String text) {
        this.counter++;
    }

    public int getCounter() {
        return counter;
    }
}
