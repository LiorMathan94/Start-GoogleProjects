package Exercise2;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractAnimal implements Animal {
    private static final AtomicInteger count = new AtomicInteger(0);
    protected final int id;
    protected final Gender gender;
    protected int weight;

    public AbstractAnimal(int weight, Gender gender) {
        this.id = count.incrementAndGet();
        this.gender = gender;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Gender getGender() {
        return gender;
    }

    protected boolean isMatable(Animal partner) {
        AbstractAnimal animal = (AbstractAnimal) partner;

        return animal.getGender() != this.getGender() &&
                animal.getClass() == this.getClass();
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "ID: " + getId() +
                ", Gender: " + getGender().toString() +
                ", Weight: " + getWeight() +
                "}";
    }
}
