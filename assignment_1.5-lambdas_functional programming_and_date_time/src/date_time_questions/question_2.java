package date_time_questions;

import java.time.LocalDate;
import java.util.Random;

public class question_2 {

    public static String dayOrDays(int n) {
        return switch (n) {
            case 1 -> "day";
            default -> "days";
        };
    }

    public static void main(String[] args) {
        Random rand = new Random();

        // creating a random year, month, and day for test
        int rYear = rand.nextInt(2000) + 1;
        int rMonth = rand.nextInt(12) + 1;
        int maxDay = LocalDate.of(rYear, rMonth, 1).lengthOfMonth();
        int rDay = rand.nextInt(maxDay) + 1;

        // creating a LocalDate object from the random date and printing it to console
        LocalDate rDate = LocalDate.of(rYear, rMonth, rDay);
        System.out.println("\nThe random date created is: " + rDate);
        int dayCount = 0;

        // creating a while loop to look for the most recent Thursday if the date isn't
        // a Thursday, it subtracts a day from the date and adds a day to the counter
        while(!rDate.getDayOfWeek().toString().equals("THURSDAY")) {
            rDate = rDate.minusDays(1);
            dayCount++;
        }

        System.out.println("\nThe previous Thursday was " + dayCount +
                            " " + dayOrDays(dayCount) + " before, " +
                            "on " + LocalDate.of(rYear, rMonth, rDay).minusDays(dayCount));
    }
}
