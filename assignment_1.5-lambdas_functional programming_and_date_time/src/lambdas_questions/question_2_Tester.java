package lambdas_questions;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class question_2_Tester {

    @Test
    public void shouldBeEven() {
        Assert.assertEquals('e', question_2.evenOrOdd(2));
    }

    @Test
    public void shouldBeOdd() {
        Assert.assertEquals('o', question_2.evenOrOdd(1));
    }

    @Test
    public void parserTest() {
        List<Integer> numList = Arrays.asList(3, 44);
        String expectedRes = "o3,e44";
        Assert.assertEquals(expectedRes, question_2.parser(numList));
    }
}
