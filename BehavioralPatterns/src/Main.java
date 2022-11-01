import exercise1.*;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        visitorClient();
    }

    private static void visitorClient() {
        List<JsonExportable> exportables = new ArrayList<>();

        exportables.add(new User(1, "Shlompi", "123456"));
        exportables.add(new Asset(1, "Shlompi", 9.8));
        exportables.add(new Group(1, 200));

        JsonStringExportingVisitor jsonVisitor = new JsonStringExportingVisitor();
        for (JsonExportable exportable : exportables) {
            exportable.accept(jsonVisitor);
        }
    }
}