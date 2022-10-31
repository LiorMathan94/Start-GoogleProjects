package Exercise2;

public class DancingRaccoon implements Animal {
    private final Raccoon raccoon;

    public DancingRaccoon(Raccoon raccoon) {
        this.raccoon = raccoon;
    }

    public int getId() {
        return raccoon.getId();
    }

    public Gender getGender() {
        return raccoon.getGender();
    }

    public int getWeight() {
        return raccoon.getWeight();
    }

    public void setWeight(int weight) {
        raccoon.setWeight(weight);
    }

    public void move() {
        raccoon.move();
    }

    public Animal mate(Animal partner) {
        if (partner.getClass() == this.getClass()) {
            partner = ((DancingRaccoon) partner).raccoon;
        }

        return raccoon.mate(partner);
    }

    public void dance() {
        System.out.println("Raccoon is dancing!!!");
    }
}
