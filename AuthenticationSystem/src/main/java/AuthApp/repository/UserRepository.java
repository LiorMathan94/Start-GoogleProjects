package AuthApp.repository;

import AuthApp.service.User;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepository {
    private final String usersFilepath = "UsersDB";
    private Map<Integer, User> usersMap;


    private UserRepository() throws IOException {
        Files.createDirectories(Paths.get(this.usersFilepath));
        parseConfigToMap();
    }

    public User add(User user) {
        User savedUser = writeToFile(user);
        this.usersMap.put(user.getId(), savedUser);

        return savedUser;
    }

    public User update(User user) {
        User savedUser = writeToFile(user);
        this.usersMap.put(user.getId(), savedUser);

        return savedUser;
    }

    public void delete(User user) {
        File userFile = new File(getUserFilepath(user));
        userFile.delete();
        this.usersMap.remove(user.getId());
    }

    public Optional<User> getByEmail(String email) {
        for (User user : usersMap.values()) {
            if (user.getEmail().compareTo(email) == 0) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    private String getUserFilepath(User user) {
        return this.usersFilepath + File.separator + user.getId() + ".json";
    }

    public void parseConfigToMap() {
        this.usersMap = new HashMap<>();

        final File folder = new File(this.usersFilepath);
        for (final File fileEntry : folder.listFiles()) {
            try (FileInputStream fileInputStream = new FileInputStream(fileEntry.getPath())) {
                Gson gson = new Gson();
                JsonReader jsonReader = new JsonReader(new InputStreamReader(fileInputStream));
                User user = gson.fromJson(jsonReader, User.class);
                this.usersMap.put(user.getId(), user);
            } catch (IOException ex) {
                throw new RuntimeException(String.format("Error occurred while trying to open: %s", fileEntry.getPath()));
            }
        }
    }

    private User writeToFile(User user) {
        String absoluteFilePath = getUserFilepath(user);
        return FileUtils.writeObjectToJsonFile(absoluteFilePath, user);
    }
}
