package Exercise2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Farm {
    private Map<Integer, Animal> animals;

    public Farm() {
        this.animals = new HashMap<>();
    }

    public void acquire() {
        addAnimalToFarm(generateRandomAnimal());
    }

    public void addAnimalToFarm(AbstractAnimal animal) {
        animals.put(animal.getId(), animal);
    }

    public AbstractAnimal generateRandomAnimal() {
        AnimalType type = AnimalType.values()[ThreadLocalRandom.current().nextInt(0, AnimalType.values().length)];
        Gender randomGender = Gender.values()[ThreadLocalRandom.current().nextInt(0, Gender.values().length)];
        int randomWeight = ThreadLocalRandom.current().nextInt(0, 100);

        switch (type) {
            case DOG:
                return new Dog(randomWeight, randomGender);
            case CAT:
                return new Cat(randomWeight, randomGender);
            case RACCOON:
                return new Raccoon(randomWeight, randomGender);
            default:
                throw new IllegalArgumentException("This animal type is not supported!");
        }
    }

    public void mate(Animal parent1, Animal parent2) {
        try {
            AbstractAnimal babyAnimal = (AbstractAnimal) parent1.mate(parent2);
            animals.put(babyAnimal.getId(), babyAnimal);
        } catch (IllegalArgumentException ex) {
            System.out.println(">>>>>>>>>>> Invalid pair for mating!");
        }
    }

    public AbstractAnimal getAnimalByID(int id) {
        return (AbstractAnimal) animals.get(id);
    }

    @Override
    public String toString() {
        String string = "Farm{animals = \n";

        for (Animal animal : animals.values()) {
            string += animal.toString() + "\n";
        }
        string += "}";

        return string;
    }
}
