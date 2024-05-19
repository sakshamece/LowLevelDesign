import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");


        String filename = "data.txt"; // Replace with the path to your file

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            DuplicateDetector duplicateDetector = DuplicateDetector.getInstance();

            while ((line = br.readLine()) != null) {
                if (duplicateDetector.isDuplicate(line)) {
                    System.out.println("Duplicate found: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}