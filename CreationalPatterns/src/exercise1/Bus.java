package exercise1;

public class Bus implements Vehicle {

    @Override
    public void transport(Passenger passenger) {
        System.out.println(String.format("Transporting %s on a bus.", passenger.getName()));
    }
}
