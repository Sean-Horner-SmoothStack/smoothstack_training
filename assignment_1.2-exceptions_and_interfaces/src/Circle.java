public class Circle implements Shape {
    private final Double radius;

    public Circle(Double r) { radius = r; }

    public Double calculateArea() { return radius * radius * 3.142; }

    public void display() {
        System.out.printf(
                "The circle has: %n" +
                "\tradius: %.2f units%n" +
                "\tarea: %.3f units squared%n%n",
                radius, calculateArea()
        );
    }
}
