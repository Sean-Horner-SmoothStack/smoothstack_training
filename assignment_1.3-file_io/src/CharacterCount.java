import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class CharacterCount {

    public static void main(String[] args) throws IOException {
        char userChar = ' ';
        int charCount = 0;
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter the letter you'd like to count:  ");
        userChar = input.nextLine().charAt(0);

        System.out.print("Enter the text file you'd like to search:  ");
        String path = input.nextLine();

        if (userChar == ' ')
            userChar = 'a';
        if (Objects.equals(path, ""))
            path = "test/ulysses.txt";

        // trying out a try with resources block
        try (BufferedReader bReader = new BufferedReader(new FileReader(path))) {
            for (String line; (line = bReader.readLine()) != null;) {
                for(char ch: line.toCharArray()) {
                    if (ch == userChar)
                        charCount++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("I'm sorry, that text file doesn't appear to exist.");
        }

        System.out.println("The character " + userChar + " shows up " + charCount + " times in the text.");

    }
}
