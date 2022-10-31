package exercise1;

import java.util.concurrent.ThreadLocalRandom;

public class Passenger {
    private final String name;
    private VehicleFactory.VehicleType favoriteVehicleType;

    public Passenger(String name, VehicleFactory.VehicleType favoriteVehicleType) {
        this.name = name;
        this.favoriteVehicleType = favoriteVehicleType;
    }

    public static Passenger newRandomPassenger() {
        String randomName = new NameGenerator().generateRandomName();
        VehicleFactory.VehicleType randomType = VehicleFactory.VehicleType.values()
                [ThreadLocalRandom.current().nextInt(VehicleFactory.VehicleType.values().length)];

        return new Passenger(randomName, randomType);
    }

    public String getName() {
        return name;
    }

    public VehicleFactory.VehicleType getFavoriteVehicleType() {
        return favoriteVehicleType;
    }
}
