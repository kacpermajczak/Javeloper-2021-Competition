package com.example.weather;

public class MailProviderSpy implements MailProvider{

    int counter = 0;

    @Override
    public void sendMail(Weather weather) {
        this.counter++;
    }

    public int getCounter() {
        return counter;
    }
}
