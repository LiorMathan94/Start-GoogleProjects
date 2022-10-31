package exercise1;

public class Taxi implements Vehicle {

    @Override
    public void transport(Passenger passenger) {
        System.out.println(String.format("Transporting %s in a taxi.", passenger.getName()));
    }
}
