package lambdas_questions;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class question_2 {

    public static char evenOrOdd(Integer num) {
        if (num%2 == 0)
            return 'e';
        else
            return 'o';
    }

    public static List<Integer> userInput() {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter the list of integers, comma separated: ");
        return Arrays.stream(input.nextLine()
                .split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static String parser(List<Integer> list) {
        StringBuffer result = new StringBuffer();
        list.forEach((num) -> result.append(evenOrOdd(num) + String.valueOf(num) + ','));
        return result.toString()
                .substring(0, result.toString().length()-1);
    }

    public static void main(String[] args) {
        List<Integer> tbChanged = null;
        if (args.length > 0) {
            tbChanged = Arrays.stream(args)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } else {
            Scanner input = new Scanner(System.in);
            tbChanged = userInput();
        }

        System.out.println(parser(tbChanged));
    }
}
