public class CommandLineConglomeration {

    public static void main(String[] args) {
        float number_sum = 0;
        StringBuffer string_conglom = new StringBuffer();

        for (String arg: args) {
            try {
                number_sum += Float.parseFloat(arg);
            } catch (NumberFormatException e) {
                string_conglom.append(arg);
            }
        }

        System.out.printf("\nThe sum of all numbers is:\t%f\n\n", number_sum);
        System.out.printf("The strings concatenate to:\t%s\n", string_conglom);
    }
}
