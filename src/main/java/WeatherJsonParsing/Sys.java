package WeatherJsonParsing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Sys {

    @JsonProperty("country")
    String country;
    @JsonProperty("sunrise")
    int sunrise;
    @JsonProperty("sunset")
    int sunset;
    @JsonProperty("id")
    int id;
    @JsonProperty("type")
    int type;
    @JsonProperty("message")
    double message;

}
