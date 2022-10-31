import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;


public class User {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int Id;
    private String Name;
    private boolean IsActivated;

    public User(String name) {
        Name = name;
        Id = count.incrementAndGet();
        Random rd = new Random();
        IsActivated = rd.nextBoolean();
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public boolean isActivated() {
        return IsActivated;
    }

    static User generateRandomUser() {
        int randomLength = 2 + (int)(Math.random() * 10);
        String randomString = generateRandomName(randomLength);
        User randomUser = new User(randomString);

        return randomUser;
    }

    static String generateRandomName(int n) {
        String AlphaBetString = "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int) (AlphaBetString.length() * Math.random());
            sb.append(AlphaBetString.charAt(index));
        }

        return sb.toString();
    }


}
