import java.util.Arrays;
import java.util.Vector;

public class star_printing_rigorous {
    // alias method for println()
    public static void output(String str) {
        System.out.println(str);
    }

    // bespoke method for multi-printing strings
    public static String multiPrint(char c, int n) {
        return new String(new char[n]).replace('\0', c);
    }

    // unnecessarily convoluted for paradigm practice reasons
    public static String dotUpdate(String str) {
        return new String(new char[str.length()+1]).replace('\0', '.');
    }

    // bespoke method for creating enhanced for loops over a range of numbers
    // mimics the Python range(a, b) function
    public static Integer[] range(int a, int b) {
        Vector<Integer> res = new Vector<Integer>();
        for (int i = a; i < b; i++) {
            res.add(i);
        }
        return Arrays.copyOf(res.toArray(), res.size(), Integer[].class);
    }

    public static void main(String[] args) {
        String dottery = ".........";

        // first pattern
        output("1)");
        for (Integer i: range(1,5)) output(multiPrint('*', i));
        output(dottery);
        dottery = dotUpdate(dottery);

        // second pattern
        output("2)");
        output(dottery);
        for (Integer i: range(1, 5)) output(multiPrint('*', 5 - i));
        output(dottery);
        dottery = dotUpdate(dottery);

        // third pattern
        output("3)");
        for (Integer i: range(0, 4)) output(multiPrint(' ', 5 - i) + multiPrint('*', 1 + (2 * i)));
        dottery = dotUpdate(dottery);

        // fourth pattern
        output("4)");
        output(dottery);
        for (Integer i: range(0, 4)) output(multiPrint(' ', 2 + i) + multiPrint('*', 7 - (2*i)));
    }
}
