package Exercise2;

public class WoodenHorse implements WoodenStructures {

    public void roll() {
        System.out.println("Wooden horse is rolling");
    }

    public WoodenStructures replicate() {
        return new WoodenHorse();
    }
}
