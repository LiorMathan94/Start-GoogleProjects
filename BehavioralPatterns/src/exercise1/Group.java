package exercise1;

public class Group implements JsonExportable {
    private final int id;
    private int size;

    public Group(int id, int size) {
        this.id = id;
        this.size = size;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
