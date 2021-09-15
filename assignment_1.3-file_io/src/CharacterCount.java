import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class CharacterCount {

    public static void main(String[] args) throws IOException {
        File file = null;
        FileReader fReader = null;
        BufferedReader buffReader = null;
        char c = ' ';
        int charCount = 0;
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter the letter you'd like to count:  ");
        c = input.nextLine().charAt(0);

        System.out.print("Enter the text file you'd like to search:  ");
        String path = input.nextLine();

        if (c == ' ')
            c = 'a';
        if (Objects.equals(path, ""))
            path = "test/ulysses.txt";

        try {
            fReader = new FileReader(path);
            buffReader = new BufferedReader(fReader);

            String line = "";
            do {
                line = buffReader.readLine();
                for (char ch: line.toCharArray()) {
                    if (ch == c)
                        charCount++;
                }
            } while (buffReader.);

        } catch (FileNotFoundException e) {
            System.out.println("I'm sorry, that text file doesn't appear to exist.");
        }

    }
}
