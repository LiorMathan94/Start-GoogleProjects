package exercise1;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
    public static <T> T generateRandomObject(List<T> elements) {
        Random rand = new Random();
        T randomElement = elements.get(rand.nextInt(elements.size()));

        return randomElement;
    }

    public static int generateRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
