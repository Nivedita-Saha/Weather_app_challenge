package com.weatherapp.myweatherapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.weatherapp.myweatherapp")
public class MyweatherappApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyweatherappApplication.class, args);
    }
}
