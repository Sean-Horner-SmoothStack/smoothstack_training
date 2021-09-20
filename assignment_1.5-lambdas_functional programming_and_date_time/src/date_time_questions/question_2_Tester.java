package date_time_questions;

import org.junit.Assert;
import org.junit.Test;

public class question_2_Tester {

    @Test
    public void shouldBeDay() {
        Assert.assertEquals("day", question_2.dayOrDays(1));
    }

    @Test
    public void shouldBeDays() {
        Assert.assertEquals("days", question_2.dayOrDays(2));
    }

    @Test
    public void shouldAlsoBeDays() {
        Assert.assertEquals("days", question_2.dayOrDays(0));
    }
}
