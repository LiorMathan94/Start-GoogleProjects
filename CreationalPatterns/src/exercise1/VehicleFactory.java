package exercise1;

public class VehicleFactory {
    public enum VehicleType {
        BUS(Bus.class.getName()),
        TAXI(Taxi.class.getName()),
        BOAT(Boat.class.getName()),
        PLANE(Plane.class.getName());

        private String className;

        VehicleType(String className) {
            this.className = className;
        }

        public static VehicleType fromString(String text) {
            for (VehicleType type : VehicleType.values()) {
                if (type.className.equalsIgnoreCase(text)) {
                    return type;
                }
            }

            return null;
        }
    }

    private static VehicleFactory instance = null;

    private VehicleFactory() {

    }

    public static VehicleFactory getInstance() {
        if (instance == null) {
            instance = new VehicleFactory();
        }

        return instance;
    }

    public Vehicle createVehicle(VehicleType type) {
        switch (type) {
            case BUS:
                return new Bus();
            case TAXI:
                return new Taxi();
            case BOAT:
                return new Boat();
            case PLANE:
                return new Plane();
            default:
                throw new IllegalArgumentException(String.format("Store type not supported: %s", type));
        }
    }
}

