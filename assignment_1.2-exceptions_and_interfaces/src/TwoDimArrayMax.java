import java.util.Random;

public class TwoDimArrayMax {

    public static int[][] array_builder() {
        Random rando = new Random();

        int[][] arr = new int[20][20];

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                arr[i][j] = rando.nextInt(300);
            }
        }

        return arr;
    };

    public static String array_max(int[][] arr) {
        int max_val = 0;
        int pos_x = 0, pos_y = 0;

        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr[0].length; y++) {
                if (arr[x][y] > max_val) {
                    max_val = arr[x][y];
                    pos_x = x;
                    pos_y = y;
                }
            }
        }

        return String.format("%d,%d,%d", max_val, pos_x, pos_y);
    };

    public static void main(String[] args) {
       int[][] arr = array_builder();
       String result = array_max(arr);
       String[] results = result.split(",");
       System.out.printf("\nThe maximum value of the array is:  %s\n", results[0]);
       System.out.printf("It's position in the array is:      row = %s, col = %s", results[1], results[2]);
    }
}
