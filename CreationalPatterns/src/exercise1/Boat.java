package exercise1;

public class Boat implements Vehicle {

    @Override
    public void transport(Passenger passenger) {
        System.out.println(String.format("Transporting %s on a boat.", passenger.getName()));
    }
}
