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

        System.out.println("Testing the various shape classes and methods now...\n");

        System.out.println("Now testing the rectangle class:");
        rectangle.display();

        System.out.println("Now testing the triangle class:");
        triangle.display();

        System.out.println("Now testing the circle class:");
        circle.display();
    }
}
