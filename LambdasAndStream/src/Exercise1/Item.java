package Exercise1;

import java.time.LocalDate;
import java.time.Year;
import java.util.concurrent.ThreadLocalRandom;

public class Item implements Comparable<Item>{
    private final String name;
    private final ProductType type;
    private final LocalDate expirationDate;
    private final double weight;

    public Item(ProductType type, LocalDate expirationDate, double weight) {
        this.name = generateRandomString();
        this.type = type;
        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    private String generateRandomString(){
        int stringLength = ThreadLocalRandom.current().nextInt(12);

        String AlphaBetString = "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(stringLength);

        for (int i = 0; i < stringLength; i++) {
            int index = (int) (AlphaBetString.length() * Math.random());
            sb.append(AlphaBetString.charAt(index));
        }

        return sb.toString();
    }

    public static Item getRandomItem() {
        ProductType randomType = ProductType.getRandomType();
        double randomWeight = ThreadLocalRandom.current().nextDouble(0.1, 50.0);
        LocalDate randomDate = generateRandomExpiryDate();

        return new Item(randomType, randomDate, randomWeight);
    }

    private static LocalDate generateRandomExpiryDate() {
        int currentYear = Year.now().getValue();
        int randomYear = ThreadLocalRandom.current().nextInt(currentYear - 5, currentYear + 5);
        int randomMonth = ThreadLocalRandom.current().nextInt(1, 13);
        int randomDay = ThreadLocalRandom.current().nextInt(1, 29);

        return LocalDate.of(randomYear, randomMonth, randomDay);
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Item o) {
        return this.getExpirationDate().compareTo(o.getExpirationDate());
    }
}
