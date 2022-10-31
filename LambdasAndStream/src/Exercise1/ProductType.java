package Exercise1;

import java.util.concurrent.ThreadLocalRandom;

public enum ProductType {
    MILK,
    BREAD,
    EGGS;

    public static ProductType getRandomType() {
        return ProductType.values()[ThreadLocalRandom.current().nextInt(ProductType.values().length)];
    }
}
