package date_time_questions;

import java.time.LocalDate;
import java.util.Scanner;

public class question_7 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter the year: ");
        int year = Integer.parseInt(input.nextLine());
        System.out.print("Please enter the month: ");
        int month = Integer.parseInt(input.nextLine());
        System.out.print("Please enter the day: ");
        int day = Integer.parseInt(input.nextLine());

        String dayOfWeek = LocalDate.of(year, month, day).getDayOfWeek().toString();

        System.out.println("\n" + (day == 13 && dayOfWeek.equals("Friday")));

    }
}
