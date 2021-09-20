import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class assignment_5_recursion {

    public static boolean needsCondensing(int[] inputArr) {
        for (int i = 0; i < inputArr.length-1; i++) {
            if (inputArr[i] == inputArr[i+1])
                return true;
        }

        return false;
    }

    public static int[] arrayCondensing(int[] inputArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < inputArr.length-1; i++) {
            if (inputArr[i] == (inputArr[i + 1])) {
                int summation = inputArr[i++] + inputArr[i++];

                while (inputArr[i] == (inputArr[i + 1]))
                    summation += inputArr[i++];

                sb.append(summation + ',');
            } else
                sb.append(inputArr[i] + ',');
        }

        String[] tokens = sb.substring(0, sb.length()-1).split(",");
        int[] result = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            result[i] = Integer.parseInt(tokens[i]);
        }
        return result;
    }

//    public static boolean groupSumClump(int x, int[] group, int target) {
//        if (needsCondensing(group))
//            return groupSumClump(x, arrayCondensing(group), target);
//
//
//
//    }

    public static void main(String[] args) {

    }
}
