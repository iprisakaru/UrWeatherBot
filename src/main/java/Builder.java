import java.text.DecimalFormat;

public class Builder {

    private String name;
    private Double temperature;
    private Double humidity;
    private String main;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemperature() {
        temperature = -273 + temperature;
        String formattedDouble = new DecimalFormat("#0.0").format(temperature);
        return formattedDouble;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }
}
