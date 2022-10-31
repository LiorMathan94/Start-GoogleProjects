package Exercise1;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigFileHandler<K, V> {
    private final String filename;
    private Map<K, V> jsonMap;
    private boolean isInitialized = false;

    public ConfigFileHandler(String filename) {
        this.filename = filename;
        parseConfigToMap();
    }

    public V getJsonValue(K key) {
        if (!isInitialized) {
            throw new IllegalStateException("jsonMap object is not initialized.");
        }
        if (jsonMap == null) {
            throw new NullPointerException("jsonMap object is null.");
        }
        if (!jsonMap.containsKey(key)) {
            throw new IllegalArgumentException(String.format("Key: %s does not exist in the json file.", key.toString()));
        }

        return jsonMap.get(key);
    }

    public void parseConfigToMap() {
        try (FileInputStream fileInputStream = new FileInputStream(filename)) {
            Gson gson = new Gson();
            JsonReader jsonReader = new JsonReader(new InputStreamReader(fileInputStream));
            this.jsonMap = gson.fromJson(jsonReader, HashMap.class);
            isInitialized = true;

        } catch (FileNotFoundException ex) {
            createDefaultConfigFile(filename);
        } catch (IOException ex) {
            throw new RuntimeException(String.format("Error occurred while trying to open: %s", filename));
        }
    }

    private void createDefaultConfigFile(String filename) {
        Map<String, String> defaultMap = new HashMap<>();
        defaultMap.put("DummyProperty", "Null");

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename)))) {
            Gson gson = new Gson();
            gson.toJson(defaultMap, writer);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Filename: \"" + filename + "\" was not found.");
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while trying to open new default json file.");
        }
    }
}
