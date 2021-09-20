package lambdas_questions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class question_1 {

    public static void main(String[] args){

        String[] inputStrings = null;

        if (args.length > 0)
            inputStrings = args;
        else
            inputStrings = new String[] {"mama", "mia", "here", "we", "go", "again", "everyone"};

        List<String> shortToLong = Arrays.stream(inputStrings)
                .sorted()
                .collect(Collectors.toList());

        List<String> longToShort = Arrays.stream(inputStrings)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        List<String> alphabeticalFirst = Stream.of(inputStrings)
                .sorted(Comparator
                        .comparingInt(str -> Character.toLowerCase(str.charAt(0))))
                .collect(Collectors.toList());

        List<String> eFirst = Stream.of(inputStrings)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("");
        for (String s : shortToLong){ System.out.println(s); }
        System.out.println("");
        for (String s : longToShort){ System.out.println(s); }
        System.out.println("");
        for (String s : alphabeticalFirst){ System.out.println(s); }
        System.out.println("");
        for (String s : eFirst){ System.out.println(s); }
        System.out.println("");
    }
}
