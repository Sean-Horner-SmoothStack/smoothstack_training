public class Triangle implements Shape {

    private Double height;
    private Double base;

    public Triangle(Double h, Double b) {
        height = h;
        base = b;
    }

    public Double calculateArea() {
        return 0.5 * base * height;
    }

    public void display() {
//        int line_len;
//        if (base > 15) {
//            line_len = 15;
//        } else {
//            line_len = (int) Math.round(base);
//        }
//
//        for (int i = line_len; i > 0; i--) {
//            String spacing = new String(new char[line_len]).replace('\0', ' ');
//            String line = new String(new char[i]).replace('\0', '*');
//        }

        System.out.print("""
                ***************
                 *************
                  ***********
                   *********
                    *******
                     *****
                      ***
                       *
                """);



    }

}
