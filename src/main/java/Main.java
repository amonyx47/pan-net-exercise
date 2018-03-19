import WeatherJsonParsing.CityWeather;
import exceptions.VariableNotSetException;
import helpers.JsonReader;
import helpers.OpenPortsChecker;
import helpers.PortScanResult;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static helpers.EnvVarRetriever.*;

public class Main {

    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather";

    public static void main(String[] args) throws VariableNotSetException, IOException, JSONException, ExecutionException, InterruptedException {
        exerciseOne();
        //exerciseTwo(args);
    }

    private static void exerciseOne() throws VariableNotSetException, IOException {
        JSONObject json = JsonReader.readJsonFromUrl(WEATHER_API_URL
                + "?q=" + retrieve("CITY_NAME")
                + "&appid=" + retrieve("OPENWEATHER_API_KEY"));
        CityWeather cityWeather = JsonReader.convertToCityWeather(json.toString());
        System.out.println("source:" + WEATHER_API_URL + ", city:" + cityWeather.getName()
                + ", description:" + cityWeather.getWeather().get(0).getDescription()
                + ", temp:" + cityWeather.getMainWeatherInfo().getTemp()
                + ", humidity:" + cityWeather.getMainWeatherInfo().getHumidity());
    }

    private static void exerciseTwo(String [] hosts) throws ExecutionException, InterruptedException {
        OpenPortsChecker openPortsChecker = new OpenPortsChecker();
        for (String host : hosts) {
            ArrayList<PortScanResult> newlyDiscoveredOpenPorts = openPortsChecker.run(host.trim());
            if(newlyDiscoveredOpenPorts.size() == 0){
                System.out.println("Host: " + host + "No new records found since the last scan.");
            }else {
                for (PortScanResult portScanResult : newlyDiscoveredOpenPorts) {
                    System.out.println("Host: " + portScanResult.getPort()
                            + " Port: " + portScanResult.getPort()
                            + " Open: " + String.valueOf(portScanResult.isOpen()));
                }
            }
        }
    }
}
