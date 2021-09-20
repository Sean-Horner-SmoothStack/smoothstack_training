import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AssignmentTester {

    @Test
    public void shouldBeEven() {
        String expectedValue = "EVEN";
        String value = assignment_1_lambdas.operationalSwitch(1, 18);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void shouldBeOdd() {
        String expectedValue = "ODD";
        String value = assignment_1_lambdas.operationalSwitch(1, 53);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void shouldBePrime() {
        String expectedValue = "PRIME";
        String value = assignment_1_lambdas.operationalSwitch(2, 13);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void shouldBeComposite() {
        String expectedValue = "COMPOSITE";
        String value = assignment_1_lambdas.operationalSwitch(2, 18);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void shouldBePalindrome() {
        String expectedValue = "PALINDROME";
        String value = assignment_1_lambdas.operationalSwitch(3, 1478998741);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void shouldBePalindrome_2() {
        String expectedValue = "PALINDROME";
        String value = assignment_1_lambdas.operationalSwitch(3, 147898741);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void shouldntBePalindrome() {
        String expectedValue = "NON-PALINDROME";
        String value = assignment_1_lambdas.operationalSwitch(3, 12334321);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void outsideOperationalParams() {
        String expectedValue = "Invalid Test Choice.";
        String value = assignment_1_lambdas.operationalSwitch(4, 14);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void a2_lambda() {
        List<Integer> input = List.of(1254, 1, 23, 0, 563, 12);
        List<Integer> expectedResult = List.of(4, 1, 3, 0, 3, 2);
        List<Integer> result = assignment_2_functional.onesPlace(input);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void a3_lambda() {
        List<Integer> input = List.of(1254, 1, 23, 0, 563, 12);
        List<Integer> expectedResult = List.of(2508, 2, 46, 0, 1126, 24);
        List<Integer> result = assignment_3_functional.doubler(input);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void a4_lambda() {
        List<String> input = List.of("xxgxb", "xdefx", "x", "afe", "gscsxfgfxx");
        List<String> expectedResult = List.of("gb", "def", "", "afe", "gscsfgf");
        List<String> result = assignment_4_functional.exingXs(input);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void a5_needsCondensing() {
        int[] input = {1,2,2,2,3,5,2};
        boolean expectedResult = true;
        boolean result = assignment_5_recursion.needsCondensing(input);
        Assert.assertEquals(expectedResult, result);
    }

//    @Test
//    public void a5_arrayCondensing() {
//        int[] input = {1,2,2,2,3,5,2};
//        String expectedResult = "[1,6,3,5,2]";
//        String result = Arrays.toString(assignment_5_recursion.arrayCondensing(input));
//        Assert.assertEquals(expectedResult, result);
//    }

}
