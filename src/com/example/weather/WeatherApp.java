package com.example.weather;

import java.util.Random;

interface WeatherConnector {
    String[] weather(String location);
}
interface MailProvider {
    void sendMail(final String location, final String weatherDatum, final String datum);
}

interface WeatherLogger {
    void log(Object object);
    void log(String text);
}

public class WeatherApp {

    static final String[] locations = new String[]{"Cracow", "Warsaw", "London", "Lodz", "Kielce", "Tokyo", "NewYork", "Buenos Aires", "Rzeszow"};

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            WeatherController weatherController = new WeatherController(new WeatherLoggerUsingSystem());

            weatherController.execute(locations);
        };

        for (int i = 0; i < locations.length * 20 ; i++) {
            new Thread(task).join();
        }
    }
}

final class WeatherController {
    private final WeatherLogger weatherLogger;

    public WeatherController(WeatherLogger weatherLogger) {
        this.weatherLogger = weatherLogger;
    }

    public void execute(String[] locations) {
        WeatherProviderUtilsCommonHelper provider = new WeatherProviderUtilsCommonHelper();

        Random random = new Random();
        int randomNumber = random.ints(0, locations.length)
                .findFirst()
                .getAsInt();

        String location = locations[randomNumber];

        weatherLogger.log(location);

        Weather weather = provider.checkWeatherAndSendMailWithTemperature(location);

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

class WeatherProviderUtilsCommonHelper {
    private WeatherConnector weatherConnector;
    private MailProvider mailProvider;

    public Weather checkWeatherAndSendMailWithTemperature(String location) {

        try {
            String[] weatherData = weatherConnector.weather(location);

            Weather weather = new Weather(weatherData[0], Double.valueOf(weatherData[1]));

            mailProvider.sendMail(location, weatherData[0], weatherData[1]);

            return weather;
        } catch (Exception e) {
            log(e);
            return null;
        }
    }

    public void setWeatherConnector(WeatherConnector connector) {
        this.weatherConnector = connector;
    }

    private static void log(Object object) {
        System.out.println("Weather=" + object.toString());
    }

    private static void log(String text) {
        System.out.println("Weather=" + text);
    }
}

class Weather {
    private String location;
    private double temp;

    public Weather(String weatherDatum, Double valueOf) {

    }
}