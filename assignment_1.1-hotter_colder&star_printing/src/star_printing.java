import java.util.ArrayList;

public class star_printing {
    public static String patterns(int i) {
        return switch(i) {
            case 1 -> """
                    1)
                    *
                    **
                    ***
                    ****
                    .........""";
            case 2 -> """
                    2)
                    ..........
                    ****
                    ***
                    **
                    *""";
            case 3 -> """
                    3)
                         *
                        ***
                       *****
                      *******
                    ...........""";
            case 4 -> """
                    4)
                    ............
                      *******
                       *****
                        ***
                         *""";
            default -> "The Matrix has glitched!\n";
        };
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 4; i++) {
            System.out.println(patterns(i));
        }
    }
}
