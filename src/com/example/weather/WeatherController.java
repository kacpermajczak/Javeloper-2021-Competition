package com.example.weather;

import java.util.OptionalInt;
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
        int randomNumber = getRandomNumber(locations, random);

        String location = locations[randomNumber];

        weatherLogger.log(location);

        Weather weather;

        try {
            weather = weatherProvider.getWeatherFrom(location);
        } catch (CannotFindWeather exception) {
            weatherLogger.log(exception.getMessage());
            return;
        }

        mailProvider.sendMail(weather);

        weatherLogger.log(weather);
    }

    private int getRandomNumber(String[] locations, Random random) {
        OptionalInt randomNumber = random.ints(0, locations.length)
                .findFirst();

        if (randomNumber.isEmpty()) {
            throw new RuntimeException();
        }

        return randomNumber.getAsInt();
    }
}