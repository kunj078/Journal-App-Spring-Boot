package JournalApp.JournalApp.api.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

// Json response to Java Object it's called Deserialization
@Getter
@Setter
public class WeatherResponse{
    private Current current;

    @Getter
    @Setter
    public class Current{
        private int temperature;
        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
        private int feelslike;
    }
}
