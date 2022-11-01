package exercise1;

import com.google.gson.Gson;

public class JsonStringExportingVisitor implements Visitor{
    @Override
    public void visit(User user) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(user);
        System.out.println(jsonString);
    }

    @Override
    public void visit(Asset asset) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(asset);
        System.out.println(jsonString);
    }

    @Override
    public void visit(Group group) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(group);
        System.out.println(jsonString);
    }
}
