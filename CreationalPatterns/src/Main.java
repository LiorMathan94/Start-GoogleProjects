import exercise1.Passenger;
import exercise1.TravelAgency;
import exercise1.VehicleFactory;
import exercise2.JobPosition;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        testAgency();
        testBuilder();
    }

    private static void testAgency() {
        Map<VehicleFactory.VehicleType, Integer> vehicles = new HashMap<>();

        vehicles.put(VehicleFactory.VehicleType.BUS, 4);
        vehicles.put(VehicleFactory.VehicleType.BOAT, 3);
        vehicles.put(VehicleFactory.VehicleType.TAXI, 8);
        vehicles.put(VehicleFactory.VehicleType.PLANE, 1);

        TravelAgency agency = new TravelAgency(vehicles);

        for (int i = 0; i < 20; i++) {
            Passenger passenger = Passenger.newRandomPassenger();
            try {
                agency.assignVehicle(passenger);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static void testBuilder() {
        LocalDate date = LocalDate.now();
        JobPosition jobPosition = new JobPosition.Builder(date, "Title", true).setDescription("This is the description").build();
        System.out.println(jobPosition);
    }
}