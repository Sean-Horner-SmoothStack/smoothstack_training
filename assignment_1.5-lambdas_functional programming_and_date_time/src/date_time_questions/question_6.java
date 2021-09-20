package date_time_questions;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class question_6 {

    public static int userInput() {
        Scanner input = new Scanner(System.in);
        int month = 0;
        System.out.print("Please enter the month: ");
        try {
            month = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("It looks like you didn't enter a number. Please try again.");
            return userInput();
        }

        if (month < 1 || month > 12) {
            System.out.println("Number out of range to be a month. Please try again.");
            return userInput();
        } else {
            return month;
        }
    }

    public static void main(String[] args) {
        // calling the current year through .now()
        int year = LocalDate.now().getYear();
        // using the error checking userInput() method
        int month = userInput();
        // determining the last day of each month
        int maxDay = LocalDate.of(year, month, 1).lengthOfMonth();

        System.out.println("\nThe date of each Monday in the month is: ");
        // for loop which checks each day to see if it is a Monday
        for(int i = 1; i <= maxDay; i++) {
            if (LocalDate.of(year, month, i).getDayOfWeek().toString().equals("MONDAY"))
                System.out.println("\t" + LocalDate.of(year, month, i));
        }
    }
}
