package WeatherJsonParsing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Clouds {

    @JsonProperty("all")
    String all;

}
