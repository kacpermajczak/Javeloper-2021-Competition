package com.example.weather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeatherControllerTest {

    private WeatherController weatherController;
    private WeatherLoggerSpy weatherLogger;
    private MailProviderSpy mailProvider;

    @BeforeEach
    void setUp() {
        this.weatherLogger = new WeatherLoggerSpy();
        this.mailProvider = new MailProviderSpy();
        this.weatherController = new WeatherController(weatherLogger, mailProvider);
    }

    @Test
    void execute() {
        weatherController.execute(new String[]{"Cracow", "Warsaw", "London", "Lodz", "Kielce", "Tokyo", "NewYork", "Buenos Aires", "Rzeszow"});
        Assertions.assertEquals(2, weatherLogger.getCounter());
        Assertions.assertEquals(1, mailProvider.getCounter());
    }
}