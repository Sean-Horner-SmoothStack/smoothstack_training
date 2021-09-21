import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class A5_Tester {

    @Test
    public void ListCondenserBeginDuplicates() {
        List<Integer> inputList = List.of(1,1,1,2,3,4,5,8,3,2,11);
        List<Integer> expectedValue = List.of(3,2,3,4,5,8,3,2,11);
        List<Integer> value = assignment_5_recursion.ListCondenser(inputList);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void ListCondenserMidDuplicates() {
        List<Integer> inputList = List.of(1,2,2,2,3,4,5,8,3,2,11);
        List<Integer> expectedValue = List.of(1,6,3,4,5,8,3,2,11);
        List<Integer> value = assignment_5_recursion.ListCondenser(inputList);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void ListCondenserEndDuplicates() {
        List<Integer> inputList = List.of(1,2,3,4,5,8,3,2,11,11);
        List<Integer> expectedValue = List.of(1,2,3,4,5,8,3,2,22);
        List<Integer> value = assignment_5_recursion.ListCondenser(inputList);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void AltListCondenserBeginDuplicates() {
        List<Integer> inputList = List.of(1,1,1,2,3,4,5,8,3,2,11);
        List<Integer> expectedValue = List.of(3,2,3,4,5,8,3,2,11);
        List<Integer> value = assignment_5_recursion.ListCondenserAlt(inputList);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void AltListCondenserMidDuplicates() {
        List<Integer> inputList = List.of(1,2,2,2,3,4,5,8,3,2,11);
        List<Integer> expectedValue = List.of(1,6,3,4,5,8,3,2,11);
        List<Integer> value = assignment_5_recursion.ListCondenserAlt(inputList);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void AltListCondenserEndDuplicates() {
        List<Integer> inputList = List.of(1,2,3,4,5,8,3,2,11,11);
        List<Integer> expectedValue = List.of(1,2,3,4,5,8,3,2,22);
        List<Integer> value = assignment_5_recursion.ListCondenserAlt(inputList);
        Assert.assertEquals(expectedValue, value);
    }
    
}
