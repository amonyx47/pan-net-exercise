package WeatherJsonParsing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MainWeatherInfo {

    @JsonProperty("temp")
    String temp;
    @JsonProperty("temp_min")
    String temp_min;
    @JsonProperty("humidity")
    String humidity;
    @JsonProperty("pressure")
    String pressure;
    @JsonProperty("temp_max")
    String temp_max;

}
