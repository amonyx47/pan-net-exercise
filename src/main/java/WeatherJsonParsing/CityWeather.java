package WeatherJsonParsing;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CityWeather {

    @JsonProperty("dt")
    int dt;
    @JsonProperty("coord")
    Coordinates coordinates;
    @JsonProperty("visibility")
    int visibility;
    @JsonProperty("weather")
    List<Weather> weather;
    @JsonProperty("name")
    String name;
    @JsonProperty("cod")
    int cod;
    @JsonProperty("main")
    MainWeatherInfo mainWeatherInfo;
    @JsonProperty("clouds")
    Clouds clouds;
    @JsonProperty("id")
    int id;
    @JsonProperty("sys")
    Sys sys;
    @JsonProperty("base")
    String base;
    @JsonProperty("wind")
    Wind wind;

}
