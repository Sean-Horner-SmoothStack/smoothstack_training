import java.util.*;
import java.util.stream.Collectors;

public class assignment_5_recursion {

    public static List<Integer> ListCondenser(List<Integer> group) {
//        System.out.println(group);    // diagnostic output
        // creating a result List
        List<Integer> condensedGroup = new ArrayList<>();
        // summation variable for duplicate values
        int summation = 0;

        // creating a ListIterator to move through the List
        ListIterator<Integer> iter = group.listIterator();

        // creating two value stores for comparing iterator values
        // the first is set to null and the second is set to the first iterator
        // value, to set up for the first run through the algorithm
        Integer previous = null;
        Integer current = iter.next();
        // loop that continues as long as there is a next value in the iterator
        while(iter.hasNext()) {
            // first step is a value shift previous <- current <- next
            previous = current;
            current = iter.next();

            // if previous is equal to current (and until they are different) loop
            // through the values, increment summation variable by the duplicate value,
            // and, if the iterator has a next value, proceed to that next value, else
            // break the loop
            while (previous.equals(current)) {
                summation += previous;
                if (iter.hasNext()) { current = iter.next(); }
                else break;
            }

            // if summation is non-zero that means duplicate values have happened and
            // due to the structure of the summation loop, the summation variable
            // needs one more addition to be complete then added to the output List
            // if summation is zero then there was not a duplicate match and the value
            // held in previous can be added to the List (fortunately since summation
            // is zero it doesn't affect the value of previous here)
            // in either case, after the value is appended to the output List, summation
            // is set to zero
            condensedGroup.add(summation + previous);
            summation = 0;
        }
        // if the loop has been exited and previous does not have the same value as the
        // current variable, then the last two List items were not duplicates and the
        // value in current still needs to be added to the list
        if (!previous.equals(current))
            condensedGroup.add(current);

//        System.out.println(condensedGroup);    // diagnostic output
        return condensedGroup;
    }

    // deprecated ListCondenser method, using direct access
    public static List<Integer> ListCondenserAlt(List<Integer> group) {
        List<Integer> condensedGroup = new ArrayList<>();
        Integer summation = 0;
        for (int i = 0; i < group.size()-1; i++) {
            // if the current number and the next number are the same
            if (group.get(i) == group.get(i+1)) {
                // enter a summation code block that adds the two numbers together
                // while simultaneously incrementing the for loop index
                summation = group.get(i++) + group.get(i++);
                // while the current number is the same as the previous number
                // add it to the summation, while incrementing the for loop index
                while (group.get(i) == group.get(i - 1))
                    summation += group.get(i++);
                // once the while loop is broken (i.e. the current number isn't the same
                // as the previous numbers) add the summation to the output List
                condensedGroup.add(summation);
            }
            // whether the if conditional failed (this number isn't the same as the next) or
            // the program is exiting the summation code block (because the current number wasn't
            // the same as the previous numbers, the current item is added to the output List
            condensedGroup.add(group.get(i));
        }
        // once all the other numbers have been checked, the final number is added to the output List
        condensedGroup.add(group.get(group.size()-1));
        return condensedGroup;
    }

    public static boolean groupSumClump(int x, int[] group, int target) {
        // converting the int[] array into a List<Integer>
        List<Integer> groupAsList = new ArrayList<>();
        for (int i : group)
            groupAsList.add(i);
        // and passing it on to the overloaded (int, List<Integer>, int) method
        return groupSumClump(x, groupAsList, target);
    }

    public static boolean groupSumClump(int x, List<Integer> group, int target) {
//        System.out.println(group);    // diagnostic output
        // using the ListCondenser method to condense sequential repeat numbers
        List<Integer> condensedList = ListCondenser(group);
//        System.out.println(condensedList);    // diagnostic output
        // sorting list here so as not to need to multiple times since the next step
        // in the algorithm is a recursively called method
        List<Integer> sortedList = condensedList.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        return groupSumClumpPostCondense(x, sortedList, target);
    }

    public static boolean groupSumClumpPostCondense(int x, List<Integer> group, int target) {
        // testing the success and failure case conditions
        if (x == target)
            return true;
        if (group.size() == 0)
            return false;

        // if the process hasn't failed or succeeded, it carries on for another round
        // it starts by filtering the remaining list for values smaller than or equal to
        // the current difference between the target and the current sum, sorts the List
        // so the largest available number is at the head of the List
        List<Integer> trimmedList = group.stream()
                .filter(n -> n <= (target - x))
                .collect(Collectors.toList());
//        System.out.println(trimmedList);    // diagnostic output
        // popping the largest available number off the front of the list, saving it to add
        // to the current total and removing it from the list for another run through
        Integer largestNumber = trimmedList.remove(0);
//        System.out.print(trimmedList);    // diagnostic output
        // recursively calling the PostCondense method
        return groupSumClumpPostCondense(x+largestNumber, trimmedList, target);
    }

    public static void main(String[] args) {
        int[] inputArr = {1,1,2,2,2,3,4,5,8,3,2,11};
        boolean passed = groupSumClump(0, inputArr, 10);
        System.out.println(passed);
    }
}
