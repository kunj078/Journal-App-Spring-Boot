package JournalApp.JournalApp.service;

import JournalApp.JournalApp.api.response.WeatherResponse;
import JournalApp.JournalApp.cache.AppCache;
import JournalApp.JournalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;
    @Autowired
    private AppCache appCache;

    public WeatherResponse getWhether(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse != null) {
            return weatherResponse;
        } else {
            String apiTemplate = appCache.appCache.get(AppCache.keys.WEATHER_API.toString());
            if (apiTemplate == null) {
                throw new RuntimeException("Missing 'weather_api' config in AppCache");
            }

            String finalAPI = apiTemplate
                    .replace(Placeholders.API_KEY, apiKey)
                    .replace(Placeholders.CITY, city);

            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            if(response.getBody() != null) {
                redisService.set("weather_of_" + city , response.getBody(), 300l);
            }
            return response.getBody();
        }
    }
}
