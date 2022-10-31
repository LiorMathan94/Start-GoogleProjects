package Exercise1;

public enum BirdType {
    BLUE_JAY("Cyanocitta cristata") {
        @Override
        public void sing() {
            System.out.println("Blublublu");
        }
    },
    CANADA_GOOSE("Branta canadensis") {
        @Override
        public void sing() {
            System.out.println("CaCaCa");
        }
    },
    CARDINAL("Cardinalis cardinalis") {
        @Override
        public void sing() {
            System.out.println("DinaDinaDina");
        }
    },
    LOON("Gavia immer") {
        @Override
        public void sing() {
            System.out.println("LooLooLoo");
        }
    };

    BirdType(String scientificName) {
        this.scientificName = scientificName;
    }

    public final String scientificName;
    public abstract void sing();
}
