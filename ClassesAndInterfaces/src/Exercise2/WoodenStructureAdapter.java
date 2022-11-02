package Exercise2;

public class WoodenStructureAdapter implements Animal {
    private WoodenStructures woodenStructure;

    public WoodenStructureAdapter(WoodenStructures woodenStructure) {
        this.woodenStructure = woodenStructure;
    }

    @Override
    public void move() {
        woodenStructure.roll();
    }

    @Override
    public Animal mate(Animal partner) {
        if (partner instanceof WoodenStructureAdapter == false) {
            throw new IllegalArgumentException("Cannot mate with this type!");
        }

        return new WoodenStructureAdapter(woodenStructure.replicate());
    }
}
