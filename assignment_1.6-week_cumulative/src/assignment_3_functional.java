import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class assignment_3_functional {

    public static List<Integer> doubler(List<Integer> intList) {
        return intList.stream()
                .map((i) -> i + i)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        // parsing command line arguments or using default case
        List<Integer> intList;
        if (args.length > 0) {
            intList = Arrays.stream(args)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
        } else {
            intList = List.of(6, 8, 6, 8, -1);
        }

        System.out.println(doubler(intList));

//      old method, "single line" lambda replaces all of this
//        ListIterator<Integer> iter = intList.listIterator();
//        List<Integer> result = new ArrayList<>(List.of());
//        while( iter.hasNext() )
//            result.add(iter.next() * 2);

//        System.out.println(result);
    }
}
