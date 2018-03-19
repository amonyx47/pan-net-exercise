package helpers;

import WeatherJsonParsing.CityWeather;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class JsonReader {

        private static String readAll(Reader rd) throws IOException {
            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = rd.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString();
        }

        public static JSONObject readJsonFromUrl(String url) throws IOException, org.json.JSONException {
            InputStream is = new URL(url).openStream();
            JSONObject json = null;
            try {
                BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
                String jsonText = readAll(rd);
                json = new JSONObject(jsonText);
            }
            finally {
                is.close();
            }
            return json;
        }

        public static CityWeather convertToCityWeather(String json) {
            ObjectMapper mapper = new ObjectMapper();
            CityWeather cityWeather = null;
            try {
                cityWeather = mapper.readValue(json, CityWeather.class);

            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return cityWeather;
        }
}
