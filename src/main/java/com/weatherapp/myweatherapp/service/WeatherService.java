package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final VisualcrossingRepository weatherRepo;

    @Autowired
    public WeatherService(VisualcrossingRepository weatherRepo) {
        this.weatherRepo = weatherRepo;
    }

    public CityInfo forecastByCity(String city) {
        return weatherRepo.getByCity(city);
    }

    public String compareDaylightHours(String city1, String city2) {
        CityInfo cityInfo1 = weatherRepo.getByCity(city1);
        CityInfo cityInfo2 = weatherRepo.getByCity(city2);

        if (cityInfo1 == null || cityInfo2 == null) {
            return "Error: Could not retrieve data for one or both cities.";
        }

        int daylightMinutes1 = calculateDaylightMinutes(cityInfo1.currentConditions.sunrise, cityInfo1.currentConditions.sunset);
        int daylightMinutes2 = calculateDaylightMinutes(cityInfo2.currentConditions.sunrise, cityInfo2.currentConditions.sunset);

        if (daylightMinutes1 > daylightMinutes2) {
            return city1 + " has longer daylight.";
        } else if (daylightMinutes2 > daylightMinutes1) {
            return city2 + " has longer daylight.";
        } else {
            return "Both cities have equal daylight hours.";
        }
    }

    public String checkRainCondition(String city1, String city2) {
        CityInfo cityInfo1 = weatherRepo.getByCity(city1);
        CityInfo cityInfo2 = weatherRepo.getByCity(city2);

        if (cityInfo1 == null || cityInfo2 == null) {
            return "Error: Could not retrieve data for one or both cities.";
        }

        boolean isRainingCity1 = cityInfo1.currentConditions.conditions.toLowerCase().contains("rain");
        boolean isRainingCity2 = cityInfo2.currentConditions.conditions.toLowerCase().contains("rain");

        if (isRainingCity1 && isRainingCity2) {
            return "Both cities are experiencing rain.";
        } else if (isRainingCity1) {
            return city1 + " is experiencing rain.";
        } else if (isRainingCity2) {
            return city2 + " is experiencing rain.";
        } else {
            return "Neither city is experiencing rain.";
        }
    }

    private int calculateDaylightMinutes(String sunrise, String sunset) {
        try {
            String[] sunriseParts = sunrise.split(":");
            String[] sunsetParts = sunset.split(":");

            int sunriseMinutes = Integer.parseInt(sunriseParts[0]) * 60 + Integer.parseInt(sunriseParts[1]);
            int sunsetMinutes = Integer.parseInt(sunsetParts[0]) * 60 + Integer.parseInt(sunsetParts[1]);

            return sunsetMinutes - sunriseMinutes;
        } catch (Exception e) {
            System.err.println("Error parsing time: " + e.getMessage());
            return 0;
        }
    }
}
