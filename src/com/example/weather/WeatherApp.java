package com.example.weather;

import java.util.Random;

interface WeatherConnector {
    String[] weather(String location);
}
interface MailProvider {
    void sendMail(final String location, final String weatherDatum, final String datum);
}
public class WeatherApp {

    static final String[] locations = new String[]{"Cracow", "Warsaw", "London", "Lodz", "Kielce", "Tokyo", "NewYork", "Buenos Aires", "Rzeszow"};

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();

        Runnable task = () -> {
            WeatherController weatherController = new WeatherController(new WeatherLogger());

            weatherController.execute(locations);
        };

        for (int i = 0; i < locations.length * 20 ; i++) {
            new Thread(task).join();
        }
    }
}

final class WeatherController {
    private WeatherLogger weatherLogger;

    public WeatherController(WeatherLogger weatherLogger) {
        this.weatherLogger = weatherLogger;
    }

    public void execute(String[] locations) {
        WeatherProviderUtilsCommonHelper provider = new WeatherProviderUtilsCommonHelper();

        Random random = new Random();
        String location = locations[random.nextInt()];

        weatherLogger.log(location);

        Weather weather = provider.checkWeatherAndSendMailWithTemperature(location);

        weatherLogger.log(weather);
    }
}

final class WeatherLogger {
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