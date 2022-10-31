package Exercise2;

public class Farmer {

    public void moveAnimal(AbstractAnimal animal) {
        animal.move();
    }

    public AbstractAnimal getFarmAnimalById(Farm farm, int id) {
        return farm.getAnimalByID(id);
    }
}