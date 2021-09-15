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
        String dirPath = "test";
        File dir = new File("test");

        if (args.length > 0) {
            dirPath = args[0];
        } else {
            // prompt the user for which directory to call
            System.out.println();
            System.out.println("It looks like you called this program without any arguments.");
            System.out.print("Enter the directory you would like to map:  ");
            dirPath = input.nextLine();
        }

        try {
            dir = new File(dirPath);
        } catch (Exception e) {
            System.out.println("It looks like that file doesn't exist...");
        }

        rFilePrint(dir);
    }
}
