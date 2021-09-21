package com.example.weather;

import java.util.Random;

interface WeatherConnector {
    String[] weather(String location);
}

interface MailProvider {
    void sendMail(Weather weather);
}

interface WeatherLogger {
    void log(Object object);
    void log(String text);
}

public class WeatherApp {

    static final String[] locations = new String[]{"Cracow", "Warsaw", "London", "Lodz", "Kielce", "Tokyo", "NewYork", "Buenos Aires", "Rzeszow"};

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            WeatherController weatherController = new WeatherController(new WeatherLoggerUsingSystem(), new MailProviderUsingMailer());

            weatherController.execute(locations);
        };

        for (int i = 0; i < locations.length * 20 ; i++) {
            new Thread(task).join();
        }
    }
}

final class WeatherController {
    private final WeatherLogger weatherLogger;
    private final MailProvider mailProvider;

    public WeatherController(WeatherLogger weatherLogger, MailProvider mailProvider) {
        this.weatherLogger = weatherLogger;
        this.mailProvider = mailProvider;
    }

    public void execute(String[] locations) {
        WeatherProviderUtilsCommonHelper provider = new WeatherProviderUtilsCommonHelper();

        Random random = new Random();
        int randomNumber = random.ints(0, locations.length)
                .findFirst()
                .getAsInt();

        String location = locations[randomNumber];

        weatherLogger.log(location);

        Weather weather = provider.getWeatherFrom(location);

        mailProvider.sendMail(weather);

        weatherLogger.log(weather);
    }
}

final class WeatherLoggerUsingSystem implements WeatherLogger {
    public void log(Object object) {
        System.out.println("Weather=" + object.toString());
    }

    public void log(String text) {
        System.out.println("Weather=" + text);
    }
}

final class MailProviderUsingMailer implements MailProvider {

    @Override
    public void sendMail(Weather weather) {
        //todo
    }
}

class WeatherProviderUtilsCommonHelper {
    private final WeatherConnector weatherConnector;

    public WeatherProviderUtilsCommonHelper(WeatherConnector weatherConnector) {
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

final class Weather {
    private final String location;
    private final String weatherDatum;
    private final double temperature;

    public Weather(final String location, final String weatherDatum, final double temp)
    {
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