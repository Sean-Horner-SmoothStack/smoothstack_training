package date_time_questions;

import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

public class question_5 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter a year: ");
        int year = Integer.parseInt(input.nextLine());

        for (int i = 1; i < 13; i++) {
            LocalDate ldt = LocalDate.of(year, i, 1);
            System.out.println("Length of " + Month.of(i) + ": " + ldt.lengthOfMonth());
        }
    }
}
