package date_time_questions;

import org.junit.Assert;
import org.junit.Test;

public class DateTimeTests {

    @Test
    public void shouldBeDay() {
        String expectedValue = "day";
        String value = question_2.dayOrDays(1);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void shouldBeDays() {
        String expectedValue = "days";
        String value = question_2.dayOrDays(2);
        Assert.assertEquals(expectedValue, value);
    }

    @Test
    public void shouldAlsoBeDays() {
        String expectedValue = "days";
        String value = question_2.dayOrDays(0);
        Assert.assertEquals(expectedValue, value);
    }


}
