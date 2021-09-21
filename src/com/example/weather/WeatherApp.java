package com.example.weather;

public class WeatherApp {

    static final String[] locations = new String[]{"Cracow", "Warsaw", "London", "Lodz", "Kielce", "Tokyo", "NewYork", "Buenos Aires", "Rzeszow"};

    public static void main(String[] args) throws InterruptedException {
        Runnable task = () -> {
            WeatherController weatherController = new WeatherController(new WeatherLoggerUsingSystem(), new MailProviderUsingMailer(), new WeatherProviderUsingExternalSystem(new WeatherConnectorUsingExternalSystem()));

            weatherController.execute(locations);
        };

        for (int i = 0; i < locations.length * 20; i++) {
            new Thread(task).join();
        }
    }
}