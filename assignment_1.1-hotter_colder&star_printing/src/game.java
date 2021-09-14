import java.util.Scanner;
import java.util.Random;

class Game {
    public byte guess_count = 5;

    public String text(String str) {
        return switch(str) {
            case "initial" -> """
                    Please guess a number between 1 and 100:\s\s""";
            case "next" -> """
                    Please guess again:\s\s""";
            case "correct" -> """
                    
                    Awesome! You guessed the number!
                    """;
            case "in_range" -> """
                    Very close, the actual number is:\s\s""";
            case "higher" -> """
                    Nope, the number is higher than that.
                    """;
            case "lower" -> """
                    Nope, the number is lower than that.
                    """;
            case "hotter" -> """
                    You're getting hotter...
                    """;
            case "colder" -> """
                    You're getting colder...
                    """;
            case "game_over" -> """
                    Uh oh, looks like you've run out of guesses. Better luck next time!
                    """;
            default -> """
                    How did this happen?
                    """;
    };
}

public static class Main {

    public static void output(String str) {
        System.out.print(str);
    }

    public static int player_input() {
        Scanner input = new Scanner(System.in);
        int guess;
        try {
            guess = input.nextInt();
        }
        catch (Exception e) {
            System.out.print("That doesn't appear to be a whole number.\nPlease try again: ");
            guess = player_input();
        }
        
        if (guess < 1 || guess > 100) {
            System.out.print("That number isn't between 1 and 100.\nPlease try again: ");
            guess = player_input();
        }

        System.out.println();
        return guess;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        Game game = new Game();

        int magic_number = rand.nextInt(100) + 1;
        int prev_guess = 0;

        output(game.text("initial"));

        // Creating an infinite game loop that only breaks at success or game over
        while(true) {
            // Take the user's input as a guess
            int guess = player_input();

            // compare the guess to the magic number.
            // if it's spot on, a congratulations, if it's close enough a correction
            if (guess == magic_number) {
                output(game.text("correct"));
                break;
            } else if (guess < magic_number + 10 && guess > magic_number - 10) {
                output(game.text("in_range"));
                output(Integer.toString(magic_number));
                break;
            }

            game.guess_count--;
            if (game.guess_count == 0) {
                output(game.text("game_over"));
                break;
            }

            output((guess < magic_number) ? game.text("higher") : game.text("lower"));

            if (prev_guess != 0) {
                byte diff_orig = (byte) Math.abs(magic_number - prev_guess);
                byte diff_new = (byte) Math.abs(magic_number - guess);
                output((diff_orig > diff_new) ? game.text("hotter") : game.text("colder"));
            }
            prev_guess = guess;

            output(game.text("next"));
        }
    }
}

}
