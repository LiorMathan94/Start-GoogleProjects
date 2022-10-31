import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {

        // Loops:
        int[] numbers = {1, 2, 3, 4};
        int divider = 2;
        printDevided(numbers, divider);

        // Random User:
        HashMap<Integer, User> randomUsers = generateRandomUsersHashMap(10);
        List<User> usersList = new ArrayList<User>(randomUsers.values());
        User user3 = getUserById(usersList, 3);
        int numActiveUsers = countActivatedUsers(usersList);

        // Stack:
        Stack<Integer> stack = new Stack<Integer>(2);
        System.out.println(stack.Empty());
        stack.Push(1);
        stack.Push(2);
        stack.Push(3);
        stack.Push(4);
        System.out.println(stack.Empty());
        System.out.println(stack.Pop());
        System.out.println(stack.Pop());
        System.out.println(stack.Pop());
        System.out.println(stack.Pop());
        System.out.println(stack.Pop());
    }


    // Loops:
    public static void printDevided(int[] numbers, int devider) {
        for (int i = 0; i < numbers.length; i++) {
            if(numbers[i] % devider == 0) {
                System.out.println(numbers[i]);
            }
        }

        for (int num : numbers) {
            if(num % devider == 0) {
                System.out.println(num);
            }
        }

        int i = 0;
        while (i < numbers.length) {
            if(numbers[i] % devider == 0) {
                System.out.println(numbers[i]);
            }
            i++;
        }
    }

    public static HashMap<Integer, User> generateRandomUsersHashMap(int n) {
        HashMap<Integer, User> randomUsers = new HashMap<Integer, User>();
        for (int i = 0; i < n; i++) {
            User randomUser = User.generateRandomUser();
            randomUsers.put(randomUser.getId(), randomUser);
        }

        return randomUsers;
    }

    public static User getUserById(List<User> users, int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }


    public static int countActivatedUsers(List<User> users) {
        int countActive = 0;
        for (User user : users) {
            if (user.isActivated()) {
                countActive++;
            }
        }

        return countActive;
    }


    // From General Programming:
    public static void printSumOddEven(int[] numbers) {
        int sumEven = 0;
        int sumOdd = 0;

        for (Integer num : numbers) {
            if (num % 2 == 0) {
                sumEven += num;
            }
            else {
                sumOdd += num;
            }
        }

        System.out.println("Sum of even: " + sumEven);
        System.out.println("Sum of odd: " + sumOdd);
    }

    public static void writeLinesContaining(String srcFilename, String dstFilename, String key) {
        try (Scanner myReader = new Scanner(new File(srcFilename));
             FileWriter myWriter = new FileWriter(dstFilename)) {

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.contains(key)) {
                    myWriter.write(data + "\n");
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

