package exercise1;

import java.util.List;
import java.util.Random;

public class NameGenerator {
    private List<String> nameDictionary;

    public NameGenerator(String filename) {
        nameDictionary = FileUtils.getStringListFromFile(filename, 0);
    }

    public String generateRandomName() {
        Random rand = new Random();
        String randomName = nameDictionary.get(rand.nextInt(nameDictionary.size()));

        return randomName;
    }
}
