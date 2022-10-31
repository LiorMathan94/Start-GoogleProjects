package Exercise1;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        User user1 = new User("LiorMathan94");
        Guest guest1 = new Guest();

        Optional<Object> user2 = newInstanceSingleString(user1);
        Optional<Object> guest2 = newInstanceSingleString(guest1);
    }

    public static Optional<Object> newInstanceSingleString(Object object) {
        Class<?> aClass = object.getClass();

        try {
            Constructor constructor = aClass.getConstructor(String.class);

            String randomString = generateRandomString(10);
            Object newObject = constructor.newInstance(randomString);
            return Optional.of(newObject);
        }
        catch (NoSuchMethodException e) {
            return Optional.empty();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Failed to instantiate a new object", e);
        }
    }

    static String generateRandomString(int n) {
        String AlphaBetString = "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaBetString.length() * Math.random());
            sb.append(AlphaBetString.charAt(index));
        }

        return sb.toString();
    }
}
