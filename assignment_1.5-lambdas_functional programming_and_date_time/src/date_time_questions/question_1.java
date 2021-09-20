package date_time_questions;

import java.time.LocalDateTime;

public class question_1 {

    public static void main(String[] args) {
        int year = 1988;
        int month = 11;
        int day = 23;
        int hour = 17;
        int minute = 32;
        int second = 12;

//      split object method
//        LocalDate myBDay = LocalDate.of(year, month, day);
//        LocalTime myBTime = LocalTime.of(hour, minute, second);
//        LocalDateTime myBirthday = LocalDateTime.of(myBDay, myBTime);

        LocalDateTime myBirthday = LocalDateTime.of(year, month, day, hour, minute, second);
        System.out.println("\nMy birthday timestamp is: " + myBirthday);
    }
}
