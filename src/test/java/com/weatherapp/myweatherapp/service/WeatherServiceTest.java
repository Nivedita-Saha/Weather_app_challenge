package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WeatherServiceTest {

    private WeatherService weatherService;
    private VisualcrossingRepository weatherRepoMock;

    @BeforeEach
    void setUp() {
        weatherRepoMock = Mockito.mock(VisualcrossingRepository.class);
        weatherService = new WeatherService(weatherRepoMock); // Constructor updated
    }

    @Test
    void testCompareDaylightHours_LondonHasLongerDay() {
        CityInfo london = new CityInfo();
        london.currentConditions = new CityInfo.CurrentConditions();
        london.currentConditions.sunrise = "06:30";
        london.currentConditions.sunset = "18:30";

        CityInfo liverpool = new CityInfo();
        liverpool.currentConditions = new CityInfo.CurrentConditions();
        liverpool.currentConditions.sunrise = "07:00";
        liverpool.currentConditions.sunset = "18:00";

        when(weatherRepoMock.getByCity("London")).thenReturn(london);
        when(weatherRepoMock.getByCity("Liverpool")).thenReturn(liverpool);

        String result = weatherService.compareDaylightHours("London", "Liverpool");

        assertEquals("London has longer daylight.", result);
    }

    @Test
    void testCheckRainCondition_LondonIsRaining() {
        CityInfo london = new CityInfo();
        london.currentConditions = new CityInfo.CurrentConditions();
        london.currentConditions.conditions = "Rain";

        CityInfo liverpool = new CityInfo();
        liverpool.currentConditions = new CityInfo.CurrentConditions();
        liverpool.currentConditions.conditions = "Clear";

        when(weatherRepoMock.getByCity("London")).thenReturn(london);
        when(weatherRepoMock.getByCity("Liverpool")).thenReturn(liverpool);

        String result = weatherService.checkRainCondition("London", "Liverpool");

        assertEquals("London is experiencing rain.", result);
    }
}
