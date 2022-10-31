import Exercise1.ConfigFileHandler;

public class Main {
    public static void main(String[] args) {
        testConfigHandler();
    }

    private static void testConfigHandler() {
        String filename = "test2.json";

        ConfigFileHandler<String, String> configHandler = new ConfigFileHandler<>(filename);

        try {
            String valueOfLior = configHandler.getJsonValue("Lior");
            System.out.println(valueOfLior);
            String valueOfShmulik = configHandler.getJsonValue("Shmulik");
            System.out.println(valueOfLior);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}