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


    public TravelAgency(Map<VehicleFactory.VehicleType, Integer> count) {
        availableVehicles = new HashMap<>();
        vehiclesInUse = new HashMap<>();
        generateVehicles(count);
    }

    private void generateVehicles(Map<VehicleFactory.VehicleType, Integer> vehiclesToAdd) {
        VehicleFactory vehicleFactory = VehicleFactory.getInstance();

        for (VehicleFactory.VehicleType type : vehiclesToAdd.keySet()) {
            for (int i = 0; i < vehiclesToAdd.get(type); i++) {
                Vehicle newVehicle = vehicleFactory.createVehicle(type);
                this.availableVehicles.computeIfAbsent(type, k -> new ArrayList<>()).add(newVehicle);
            }
        }
    }

    public void assignVehicle(Passenger passenger) {
        VehicleFactory.VehicleType favoriteType = passenger.getFavoriteVehicleType();
        Vehicle vehicle = null;

        if (this.availableVehicles.containsKey(favoriteType) && this.availableVehicles.get(favoriteType).size() > 0) {
            vehicle = availableVehicles.get(favoriteType).get(0);
        } else {
            List<Vehicle> allVehicles = availableVehicles.values()
                    .stream().flatMap(List::stream).collect(Collectors.toList());

            if (allVehicles.size() > 0) {
                vehicle = allVehicles.get(ThreadLocalRandom.current().nextInt(allVehicles.size()));
            } else {
                throw new IllegalArgumentException (">>>>>>>>>> No available vehicles!");
            }
        }

        if (vehicle != null) {
            VehicleFactory.VehicleType type = VehicleFactory.VehicleType.fromString(vehicle.getClass().getName());
            vehiclesInUse.put(vehicle, passenger);
            availableVehicles.get(type).remove(vehicle);
        }

        if (areAllVehiclesInUse()) {
            invokeWhenAllVehicleInUse();
        }
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
