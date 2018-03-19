package WeatherJsonParsing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Wind {

    @JsonProperty("deg")
    int deg;
    @JsonProperty("speed")
    double speed;

}
