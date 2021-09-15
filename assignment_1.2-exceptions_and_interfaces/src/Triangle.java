public class Triangle implements Shape {

    private final Double height;
    private final Double base;

    public Triangle(Double h, Double b) {
        height = h;
        base = b;
    }

    public Double calculateArea() { return 0.5 * base * height; }

    public void display() {
        System.out.printf(
                "The triangle has:%n" +
                "\theight: %.2f units%n" +
                "\tbase: %.2f units%n" +
                "\tarea: %.3f units squared%n%n",
                height, base, calculateArea()
        );
    }

}
