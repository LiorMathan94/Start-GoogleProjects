package Exercise2;

import java.util.concurrent.ThreadLocalRandom;

public class Dog extends AbstractAnimal {
    private final int INITIAL_DOG_WEIGHT = 10;

    public Dog(int weight, Gender gender) {
        super(weight, gender);
    }

    @Override
    public void move() {
        System.out.println("Dog is moving...");
    }

    @Override
    public Animal mate(Animal partner) {
        if (!isMatable(partner)) {
            throw new IllegalArgumentException();
        }

        Gender randomGender = Gender.values()[ThreadLocalRandom.current().nextInt(0, Gender.values().length)];

        return new Dog(INITIAL_DOG_WEIGHT, randomGender);
    }

}
