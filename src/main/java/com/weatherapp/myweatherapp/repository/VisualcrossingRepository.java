package com.weatherapp.myweatherapp.repository;

import com.weatherapp.myweatherapp.model.CityInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class VisualcrossingRepository {

    @Value("${weather.visualcrossing.url}")
    private String url;

    @Value("${weather.visualcrossing.key}")
    private String key;

    private final RestTemplate restTemplate = new RestTemplate();

    public CityInfo getByCity(String city) {
        try {
            //  API request format
            String uri = UriComponentsBuilder.fromHttpUrl(url + city) 
                    .queryParam("key", key)
                    .queryParam("unitGroup", "metric")  
                    .queryParam("contentType", "json")
                    .queryParam("include", "current")   // Fetch current weather conditions
                    .toUriString();

            System.out.println("Requesting API: " + uri); //  Debugging: Check API request URL

            CityInfo response = restTemplate.getForObject(uri, CityInfo.class);

            if (response == null) {
                System.err.println("Error: API returned null response for city: " + city);
                return new CityInfo(); 
            }

            return response;

        } catch (HttpClientErrorException e) {
            System.err.println("API Error for city: " + city + " - " + e.getStatusCode() + " : " + e.getResponseBodyAsString());
            return new CityInfo(); // Return empty object to prevent crashes
        } catch (Exception e) {
            System.err.println("Unexpected error for city: " + city + " - " + e.getMessage());
            return new CityInfo(); // Return empty object
        }
    }
}
