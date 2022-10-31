package Exercise2;

import java.util.concurrent.ThreadLocalRandom;

public class Raccoon extends AbstractAnimal {
    private final int INITIAL_RACCOON_WEIGHT = 5;

    public Raccoon(int weight, Gender gender) {
        super(weight, gender);
    }

    @Override
    public void move() {
        System.out.println("Raccoon is moving...");
    }

    @Override
    public Animal mate(Animal partner) {
        if (!isMatable(partner)) {
            throw new IllegalArgumentException();
        }

        Gender randomGender = Gender.values()[ThreadLocalRandom.current().nextInt(0, Gender.values().length)];

        return new Raccoon(INITIAL_RACCOON_WEIGHT, randomGender);
    }
}
