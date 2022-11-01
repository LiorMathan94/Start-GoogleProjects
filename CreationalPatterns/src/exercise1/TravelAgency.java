package exercise1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class TravelAgency {
    private final Map<VehicleFactory.VehicleType, List<Vehicle>> availableVehicles;
    private final Map<Vehicle, Passenger> vehiclesInUse;


    public TravelAgency(Map<VehicleFactory.VehicleType, Integer> countByType) {
        availableVehicles = new HashMap<>();
        vehiclesInUse = new HashMap<>();
        generateVehicles(countByType);
    }

    private void generateVehicles(Map<VehicleFactory.VehicleType, Integer> countByType) {
        VehicleFactory vehicleFactory = VehicleFactory.getInstance();

        for (VehicleFactory.VehicleType type : countByType.keySet()) {
            for (int i = 0; i < countByType.get(type); i++) {
                Vehicle newVehicle = vehicleFactory.createVehicle(type);
                this.availableVehicles.computeIfAbsent(type, k -> new ArrayList<>()).add(newVehicle);
            }
        }
    }

    public void assignVehicle(Passenger passenger) {
        VehicleFactory.VehicleType favoriteType = passenger.getFavoriteVehicleType();
        Vehicle vehicle = getAvailableVehicle(favoriteType);

        VehicleFactory.VehicleType type = VehicleFactory.VehicleType.fromString(vehicle.getClass().getName());

        vehiclesInUse.put(vehicle, passenger);
        availableVehicles.get(type).remove(vehicle);

        if (areAllVehiclesInUse()) {
            invokeWhenAllVehicleInUse();
        }
    }

    private Vehicle getAvailableVehicle(VehicleFactory.VehicleType favoriteType) {
        if (isVehicleTypeAvailable(favoriteType)) {
            return availableVehicles.get(favoriteType).get(0);
        } else {

            List<Vehicle> allVehicles = availableVehicles.values()
                    .stream().flatMap(List::stream).collect(Collectors.toList());

            if (allVehicles.size() == 0) {
                throw new IllegalArgumentException(">>>>>>>>>> No available vehicles!");
            }

            return allVehicles.get(ThreadLocalRandom.current().nextInt(allVehicles.size()));
        }
    }

    private boolean isVehicleTypeAvailable(VehicleFactory.VehicleType type) {
        return this.availableVehicles.containsKey(type) && this.availableVehicles.get(type).size() > 0;
    }

    private boolean areAllVehiclesInUse() {
        return availableVehicles.values().stream().flatMap(List::stream).collect(Collectors.toList()).size() == 0;
    }

    private void invokeWhenAllVehicleInUse() {
        for (Vehicle vehicle : vehiclesInUse.keySet()) {
            vehicle.transport(vehiclesInUse.get(vehicle));
        }
    }

}
