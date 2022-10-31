package Exercise2;

public class Main {
    public static void main(String[] args) {
        testFarm();
    }

    public static void testFarm() {
        Farm farm = new Farm();

        farm.acquire();
        farm.acquire();
        farm.acquire();

        System.out.println(farm.toString());

        // ---------------------------------------

        farm.addAnimalToFarm(new Dog(10, Gender.MALE));
        farm.addAnimalToFarm(new Dog(10, Gender.FEMALE));
        farm.addAnimalToFarm(new Dog(10, Gender.FEMALE));

        farm.mate(farm.getAnimalByID(4), farm.getAnimalByID(5));
        farm.mate(farm.getAnimalByID(5), farm.getAnimalByID(6));

        System.out.println(farm.toString());

        // ---------------------------------------

        DancingRaccoon dancingRaccoon1 = new DancingRaccoon(new Raccoon(10, Gender.FEMALE));
        DancingRaccoon dancingRaccoon2 = new DancingRaccoon(new Raccoon(10, Gender.MALE));
        Raccoon Raccoon = new Raccoon(10, Gender.MALE);

        dancingRaccoon1.dance();
        dancingRaccoon1.move();
        dancingRaccoon1.mate(dancingRaccoon2);
        dancingRaccoon1.mate(Raccoon);

        Farmer farmer = new Farmer();

        AbstractAnimal animal5 = farmer.getFarmAnimalById(farm, 5);
        System.out.println(animal5);
        farmer.moveAnimal(animal5);
    }
}