# MyWeather App Tech Test

Welcome to the MyWeather App Tech Test.

## The Challenge

You are tasked with implementing two new features in the app:

1. **Daylight Hours Comparison:** Given two city names, compare the length of the daylight hours between them and return the city with the longest day. In this context, "daylight hours" means the time between sunrise and sunset.

2. **Rain Check:** Given two city names, check which city it is currently raining in.

In addition to implementing these 2 features, you will also need to write tests verifying that your code works as expected.

If possible, include exception handling in the controller.

Finally, you can write any documentation as you see fit, explaining your choices and how the code works.

## The Codebase

The codebase is a Java application built with the Spring framework. It includes a `WeatherController` class where you will add your new features.

## Implementation Details

You will need to implement these features by adding new endpoints to the `WeatherController`.

### Prerequisites

- [Java sdk 17](https://openjdk.java.net/projects/jdk/17/)
- [Maven 3.6.3+](https://maven.apache.org/install.html)
- API key for [Visual Crossing Weather API](https://www.visualcrossing.com/weather-data-editions). 
  - This can be done by creating a free account on the above link. Then you will need to add your key to the `weather.visualcrossing.key` field in src/main/resources/application.properties

## Submission

* Push the downloaded version of this repo to your Github
* Make a branch for your changes
* Once you're ready to submit, raise a Pull Request to merge your changes with your main branch and share the repo with us.

Good luck!







## Implementation Choices & Approach
# MyWeather App - Spring Boot API

This is a **Spring Boot** application that fetches real-time weather data using the **Visual Crossing API**. 

The API provides:
- **Weather forecast** for a given city.
- **Daylight hours comparison** between two London and Liverpool.
- **Rain check** to see which city is experiencing rain.

---

## Installation & Setup

### Clone the repository
```bash
git clone https://github.com/Nivedita-Saha/Weather_app_challenge.git
cd Weather_app_challenge

## Ensure Java & Maven are installed

java -version
mvn -version

## Run the application
mvn spring-boot:run

## Test the API in the browser

http://localhost:8080/api/weather/
http://localhost:8080/api/weather/forecast/London
http://localhost:8080/api/weather/compare-daylight?city1=London&city2=Liverpool
http://localhost:8080/api/weather/rain-check?city1=London&city2=Liverpool

## API Endpoints

Below are the available API endpoints and their functionalities:

| **Endpoint**                     | **Method** | **Description**                            |
|-----------------------------------|-----------|--------------------------------------------|
| `/api/weather/`                   | `GET`     | Root message                              |
| `/api/weather/forecast/{city}`     | `GET`     | Get weather forecast for a city           |
| `/api/weather/compare-daylight`    | `GET`     | Compare daylight between two cities       |
| `/api/weather/rain-check`          | `GET`     | Check which city is currently raining     |

## Troubleshooting & Common Issues

**Port 8080 Already in Use Error** When running the application, if you see an error like this: Web server failed to start. Port 8080 was already in use. This means another process is already using port 8080, and Spring Boot cannot start.

**Solution: Find & Kill the Process** lsof -i :8080
Example output: COMMAND   PID         USER   FD   TYPE             DEVICE SIZE/OFF NODE NAME
java    98511 niveditasaha   36u  IPv6 0xfcb6400ac3566ea5      0t0  TCP *:http-alt (LISTEN)
The PID is 98511 (it will be different on your system). Then Kill the process using the PID: kill -9 98511
**Restart the application:** mvn spring-boot:run
## Test the API in the browser again to see the output.

## End

