package exercise1;

public class Plane implements Vehicle {

    @Override
    public void transport(Passenger passenger) {
        System.out.println(String.format("Transporting %s on a plane.", passenger.getName()));
    }
}
