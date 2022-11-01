package exercise1;

public class User implements JsonExportable {
    private final int id;
    private final String name;
    private String password;

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
