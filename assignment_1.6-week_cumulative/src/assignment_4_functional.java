import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class assignment_4_functional {

    public static List<String> exingXs(List<String> strList) {
        return strList.stream()
                .map( (str) -> {
                    StringBuilder sb = new StringBuilder();
                    for (char c: str.toCharArray()) {
                        if (c != 'x') sb.append(c);
                    }
                    return sb.toString(); } )
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // parsing command line arguments or using default case
        List<String> strList;
        if (args.length > 0) {
            strList = Arrays.asList(args);
        } else {
            strList = Arrays.asList("xxax", "xbxbx", "xxcx");
        }

        System.out.println(exingXs(strList));
    }

}
