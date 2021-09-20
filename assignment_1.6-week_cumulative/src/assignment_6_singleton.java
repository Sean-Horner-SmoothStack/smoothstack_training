import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class assignment_6_singleton {

    public static class SampleSingleton {

        private SampleSingleton() {
            System.out.println("\tA new SampleSingleton has been created!");
        }

        private static Connection conn = null;

        volatile static SampleSingleton instance = null;

        synchronized public static SampleSingleton getInstance() {
            if (instance == null) {
                System.out.println("\tNo object exists, instantiating one now...");
                instance = new SampleSingleton();
            }
//            instance.conn = new Connection("jdbc://");
            synchronized (instance) { return instance; }
        }

    }

    public static void main(String[] args) {
        System.out.println("\nGetting the first instance...");
        SampleSingleton ss1 = SampleSingleton.getInstance();
        System.out.println("\nGetting the second instance...");
        SampleSingleton ss2 = SampleSingleton.getInstance();

        System.out.println("Testing the objects for parity...");
        if (ss1.hashCode() == ss2.hashCode())
            System.out.println("\nThe objects are indeed the same!");

    }
}

