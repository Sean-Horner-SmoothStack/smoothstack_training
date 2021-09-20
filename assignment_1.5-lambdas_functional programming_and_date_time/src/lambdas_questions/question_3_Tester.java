package lambdas_questions;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class question_3_Tester {

    @Test
    public void StartAndLengthFilterTest() {
        List<String> testList =
                Arrays.asList("art", "prada", "Luigi", "are", "sabbath", "ain't");
        List<String> expectedValues = Arrays.asList("art", "are");
        Assert.assertEquals(expectedValues, question_3.StartAndLengthFilter(testList));
    }
}
