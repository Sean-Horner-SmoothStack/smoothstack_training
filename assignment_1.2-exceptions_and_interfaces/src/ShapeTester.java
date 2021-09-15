public class ShapeTester {

    public static void main(String[] args) {
        double[] rectMeasurements = new double[2];
        rectMeasurements[0] = 11.0;
        rectMeasurements[1] = 12.3;

        double[] triangleMeasurements = new double[2];
        triangleMeasurements[0] = 13.2;
        triangleMeasurements[1] = 6.8;

        double circleRadius = 13.46;

        Rectangle rectangle = new Rectangle(rectMeasurements[0], rectMeasurements[1]);
        Triangle triangle = new Triangle(triangleMeasurements[0], triangleMeasurements[1]);
        Circle circle = new Circle(circleRadius);

        System.out.println("Testing shape classes methods now.");
        System.out.println("Now testing the rectangle class:");
        System.out.printf("The rectangle measures:\theight: %.2f\twidth: %.2f\n", rectMeasurements[0], rectMeasurements[1]);
        System.out.printf("%.2f\n\n", rectangle.calculateArea());
        rectangle.display();
        System.out.println("Now testing the triangle class:");
        System.out.printf("The triangle measures:\theight: %.2f\tbase: %.2f\n", rectMeasurements[0], rectMeasurements[1]);
        System.out.printf("%.2f\n\n", triangle.calculateArea());
        triangle.display();
        System.out.println("Now testing the circle class:");
        System.out.printf("The circle has a radius of length: %.2f\n", circleRadius);
        System.out.printf("%.2f\n\n", circle.calculateArea());
        circle.display();
    }
}
