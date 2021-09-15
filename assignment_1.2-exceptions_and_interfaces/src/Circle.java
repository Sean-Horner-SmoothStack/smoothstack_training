public class Circle implements Shape {
    private Double radius;

    public Circle(Double r) {
        radius = r;
    }

    public Double calculateArea() {
        return radius * radius * 3.142;
    }

    public void display() {
//        int print_rad = 0;
//        if (radius > 10)
//            print_rad = 10;
//        else
//            print_rad = (int)Math.round(radius);

        System.out.print("""
                     ~~~~~
                   *********
                 *************
                ***************
                 *************
                   *********
                     ~~~~~
                """);

    }
}
