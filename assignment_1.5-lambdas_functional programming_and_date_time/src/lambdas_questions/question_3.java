package lambdas_questions;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class question_3 {

    public static List<String> StartAndLengthFilter(List<String> list) {
        return list.stream()
                .filter(s -> s.charAt(0) == 'a' && s.length() == 3)
                .collect(Collectors.toList());
    }

    public static List<String> UserInputLoop() {
        Scanner input = new Scanner(System.in);
        System.out.println("\nPlease enter your list of strings, comma separated:\n");
        String userInput = input.nextLine();
        if (userInput.length() == 0) {
            System.out.print("There doesn't seem to be any input, please try again.");
            return UserInputLoop();
        } else
            return Arrays.stream(userInput.split(",")).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<String> toBeCheckedList = null;
        if (args.length > 0)
            toBeCheckedList = Arrays.asList(args);
        else
            toBeCheckedList = UserInputLoop();

        System.out.println("\nThe words that meet the criteria in the given list are: ");
        StartAndLengthFilter(toBeCheckedList).forEach(System.out::println);
    }
}
