import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class assignment_1_lambdas {

    @FunctionalInterface
    interface Lambda{
        String check(Integer n);
    }

    public static String operationalSwitch(Integer mode, Integer num) {
        // setting up lambda expressions
        Lambda isOdd = n -> (n % 2 == 1) ? "ODD" : "EVEN";

        Lambda isPrime = n -> {
            for (int i = 2; (double) i <= Math.sqrt(n); i++){
                if (n % i == 0)
                    return "COMPOSITE";
            }
            return "PRIME";
        };

        Lambda isPalindrome = n -> {
            String digits = n.toString();
            for (int i = 0; i < digits.length(); i++) {
                if (digits.charAt(i) != digits.charAt(digits.length() - 1 - i))
                    return "NON-PALINDROME";
            }
            return "PALINDROME";
        };

        return switch (mode) {
            case 1 -> isOdd.check(num);
            case 2 -> isPrime.check(num);
            case 3 -> isPalindrome.check(num);
            default -> "Invalid Test Choice.";
        };
    }

    public static void main(String[] args) {
        // parsing command line arguments or using default case
        List<Integer> intList;
        if (args.length > 0)
            intList = Arrays.stream(args)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        else
            intList = List.of(5, 1, 4, 2, 5, 3, 898, 1, 3, 2, 12);

        int testCases = intList.get(0);
        System.out.println("\nThere should be " + testCases + " test cases to perform\n");

        ListIterator<Integer> iter = intList.listIterator(1);
        while (iter.hasNext()) {
            System.out.println(operationalSwitch(iter.next(), iter.next()));
        }
    }
}
