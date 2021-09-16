import java.io.File;
import java.util.Scanner;

public class DirectoryListing {

    public static void rFilePrint(File file) {
        System.out.println(file);

        if (file.isDirectory()) {
            for (File f: file.listFiles())
                rFilePrint(f);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String dirPath = "";
        File dir = null;

        if (args.length > 0) {
            if (
                args[0].equals("help") ||
                args[0].equals("h") ||
                args[0].equals("-h") ||
                args[0].equals("--h") ||
                args[0].equals("-help") ||
                args[0].equals("--help")
            )
                System.out.println("""
                        This program is for printing all of the subdirectories and files of a user-specified directory.
                        Call this program with the absolute path to the desired directory.
                        I.e. "java DirectoryListing "C:/programming_projects/"
                        """);
            else
                dirPath = args[0];
        } else {
            // prompt the user for which directory to call
            System.out.println();
            System.out.println("It looks like you called this program without any arguments.");
            System.out.print("Enter the directory you would like to map:  ");
            dirPath = input.nextLine();
        }

        if (dirPath.equals("")) {
            dirPath = "test";
            System.out.println("Using default value: /test\n");
        }

        try {
            dir = new File(dirPath);
        } catch (Exception e) {
            System.out.println("It looks like that file doesn't exist...");
            dir = new File("test");
        }

        rFilePrint(dir);
    }
}
