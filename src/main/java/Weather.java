import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

public class Weather {

    public static String getWeather(String message, Builder model) throws IOException {
        URL url = new URL("api.openweathermap.org/data/2.5/weather?q=" + message + "&appid=0a3282ba978c5341e8c4265d09b0441c");
        Scanner in = new Scanner((InputStream) url.getContent());
        String result="";
        while (in.hasNext()) {
            result += in.nextLine();
        }
        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));
        JSONObject main = object.getJSONObject("main");
        model.setTemperature(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = object.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj = getArray.getJSONObject(i);
            model.setMain((String) obj.get("main"));
        }
        return "City: " + model.getName() + '\n' + "Main: " + model.getMain() + '\n' + "Temperature :" + model.getTemperature() + " C" + '\n' +
                "Humidity: " + model.getHumidity() + " %" + '\n';

    }
}