import java.util.HashMap;
import java.util.Map;

public class City {
    private final String name;
    private final String country;
    private int population;
    private static Map<String, City> cachedCities = new HashMap<>();


    public City(String name, String country, int population) {
        this.name = name;
        this.country = country;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getPopulation() {
        return population;
    }

    public static City getTelAviv() {
        if (!cachedCities.containsKey("Tel Aviv")) {
            City telAviv = new City("Tel Aviv", "Israel", 2000000);
            cachedCities.put(telAviv.name, telAviv);
        }

        return cachedCities.get("Tel Aviv");
    }
}
