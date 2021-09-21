package com.example.weather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherControllerTest {

    private WeatherController weatherController;
    private WeatherLoggerSpy weatherLogger;

    @BeforeEach
    void setUp() {
        this.weatherLogger = new WeatherLoggerSpy();
        this.weatherController = new WeatherController(weatherLogger);
    }

    @Test
    void execute() {
        weatherController.execute(new String[]{"Cracow", "Warsaw", "London", "Lodz", "Kielce", "Tokyo", "NewYork", "Buenos Aires", "Rzeszow"});
        Assertions.assertEquals(2, weatherLogger.getCounter());
    }
}