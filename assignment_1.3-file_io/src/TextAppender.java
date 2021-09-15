import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TextAppender {

    public static void main(String[] args) throws IOException {
        FileWriter fWriter = null;
        BufferedWriter bWriter = null;
        PrintWriter pWriter = null;
        Scanner in = new Scanner(System.in);
        String path = "test/append_test.txt";
        List<String> appendLines = new ArrayList<>();

        // prompting the user for a different
        System.out.print("If desired, please enter a unique path (default: test/append_test.txt): ");
        String answer = in.nextLine();
        if (!answer.isBlank() && answer.endsWith(".txt"))
            path = answer;

        // if the program is called with no arguments to append, then it asks for some.
        if (args.length == 0) {
            System.out.println("It looks like you called this program with no arguments.");
            System.out.println("What would you like to append to the file? (Enter an empty line, press return twice, to stop):");
            do {
                String appending = in.nextLine();
                if (appending.isBlank())
                    break;
                else
                    appendLines.add(appending);
            } while (in.hasNextLine());
        } else {
            appendLines.addAll(Arrays.asList(args));
        }


        try {
            fWriter = new FileWriter(path, true);
            bWriter = new BufferedWriter((fWriter));
            pWriter = new PrintWriter(bWriter);

            for (String line: appendLines)
                pWriter.println(line);

            System.out.println("Finished appending lines to " + path);
            pWriter.flush();
        } catch (IOException ioe) {
            // there is no file there.
        } finally {
            if (pWriter != null)
                pWriter.close();
            if (bWriter != null)
                bWriter.close();
            if (fWriter != null)
                fWriter.close();
        }

    }
}
