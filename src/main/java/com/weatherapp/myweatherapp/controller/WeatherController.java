package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather") 
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    // Root endpoint to avoid 404 errors when accessing http://localhost:8080/
    @GetMapping("/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Welcome to the Weather API! Try /api/weather/forecast/London");
    }

    //  Get weather forecast for a city
    @GetMapping("/forecast/{city}")
    public ResponseEntity<CityInfo> forecastByCity(@PathVariable("city") String city) {
        CityInfo cityInfo = weatherService.forecastByCity(city);
        return ResponseEntity.ok(cityInfo);
    }

    //  Compare daylight hours between two cities
    @GetMapping("/compare-daylight")
    public ResponseEntity<String> compareDaylight(@RequestParam String city1, @RequestParam String city2) {
        String result = weatherService.compareDaylightHours(city1, city2);
        return ResponseEntity.ok(result);
    }

    //  Check which city is currently raining
    @GetMapping("/rain-check")
    public ResponseEntity<String> checkRain(@RequestParam String city1, @RequestParam String city2) {
        String result = weatherService.checkRainCondition(city1, city2);
        return ResponseEntity.ok(result);
    }
}
