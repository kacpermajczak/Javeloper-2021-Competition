package com.example.weather;

import java.util.Random;

final class WeatherController {
    private final WeatherLogger weatherLogger;
    private final MailProvider mailProvider;
    private final WeatherProvider weatherProvider;

    public WeatherController(WeatherLogger weatherLogger, MailProvider mailProvider, WeatherProvider weatherProvider) {
        this.weatherLogger = weatherLogger;
        this.mailProvider = mailProvider;
        this.weatherProvider = weatherProvider;
    }

    public void execute(String[] locations) {
        Random random = new Random();
        int randomNumber = random.ints(0, locations.length)
                .findFirst()
                .getAsInt();

        String location = locations[randomNumber];

        weatherLogger.log(location);

        Weather weather = weatherProvider.getWeatherFrom(location);

        mailProvider.sendMail(weather);

        weatherLogger.log(weather);
    }
}