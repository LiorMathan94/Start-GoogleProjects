package exercise1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class NameGenerator {
    private List<String> nameDictionary;

    public NameGenerator() {
        nameDictionary = getStringListFromFile("MaleNameList.txt", 0);
    }

    public String generateRandomName() {
        Random rand = new Random();
        String randomName = nameDictionary.get(rand.nextInt(nameDictionary.size()));

        return randomName;
    }

    private List<String> getStringListFromFile(String filename, int column) {
        List<String> namesList = new ArrayList<String>();
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] tokens = data.split("\\s+");
                namesList.add(tokens[column]);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return namesList;
    }
}
