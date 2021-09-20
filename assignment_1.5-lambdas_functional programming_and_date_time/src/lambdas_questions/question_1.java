package lambdas_questions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class question_1 {

    @FunctionalInterface
    interface Lambda {
        List<String> run(List<String> input);
    }

    public static void main(String[] args){
        // parsing command line arguments or using default case
        String[] inputStrings = null;
        if (args.length > 0)
            inputStrings = args;
        else
            inputStrings = new String[] {"mama", "mia", "here", "we", "go", "again", "everyone"};

        // requested sorts using the stream() API
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

        Arrays.sort(inputStrings, (str1, str2) -> {
            if (str1.charAt(0) == 'e' && str2.charAt(0) == 'e') { return 0; }
            else if (str1.charAt(0) == 'e')  { return -1; }
            else { return 1; }
        });

        List<String> eFirst = Arrays.asList(inputStrings);


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
