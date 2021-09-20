package lambdas_questions;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class LambdasTests {

    @Test
    public void shouldBeEven() {
        char expectedValue = 'e';
        char value = question_2.evenOrOdd(2);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void shouldBeOdd() {
        char expectedValue = 'o';
        char value = question_2.evenOrOdd(5);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void parserTest() {
        List<Integer> numList = Arrays.asList(3, 44);
        String expectedValue = "o3,e44";
        String value = question_2.parser(numList);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void StartAndLengthFilterTest() {
        List<String> testList = Arrays.asList("art", "prada", "Luigi", "are", "sabbath", "ain't");
        List<String> expectedValues = Arrays.asList("art", "are");
        List<String> values = question_3.StartAndLengthFilter(testList);
        Assert.assertEquals(expectedValues, values);
    }
}
