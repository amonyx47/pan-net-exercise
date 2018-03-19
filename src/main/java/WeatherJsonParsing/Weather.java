package WeatherJsonParsing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Weather {

    @JsonProperty("icon")
    String icon;
    @JsonProperty("description")
    String description;
    @JsonProperty("main")
    String mainWeatherInfo;
    @JsonProperty("id")
    int id;

}
