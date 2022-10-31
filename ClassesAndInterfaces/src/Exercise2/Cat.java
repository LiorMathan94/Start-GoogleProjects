package Exercise2;

import java.util.concurrent.ThreadLocalRandom;

public class Cat extends AbstractAnimal {
    private final int INITIAL_CAT_WEIGHT = 3;

    public Cat(int weight, Gender gender) {
        super(weight, gender);
    }

    @Override
    public void move() {
        System.out.println("Cat is moving...");
    }

    @Override
    public Animal mate(Animal partner) {
        if (!isMatable(partner)) {
            throw new IllegalArgumentException();
        }

        Gender randomGender = Gender.values()[ThreadLocalRandom.current().nextInt(0, Gender.values().length)];

        return new Cat(INITIAL_CAT_WEIGHT, randomGender);
    }

}
