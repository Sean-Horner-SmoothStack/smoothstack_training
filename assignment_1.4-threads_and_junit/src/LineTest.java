import org.junit.Assert;
import org.junit.Test;

public class LineTest {
        // remember to use the @Test decorator to tell JUnit that this is a test
        @Test
        public void slopeDivideByZero() {
            Line line = new Line(1.0, 2.0, 1.0, 5.0);
            Assert.assertThrows(ArithmeticException.class, line::getSlope);
        }

        @Test
        public void zeroSlope() {
            Line line = new Line(0, 5, 1, 5);
            Assert.assertEquals(0.0, line.getSlope(), 0.0001);
        }

        @Test
        public void slopeIsTwo() {
            Line line = new Line(0, 0, 1, 2);
            Assert.assertEquals(2.0, line.getSlope(), 0.0001);
        }

        @Test
        public void zeroDistance() {
            Line line = new Line(0,0,0,0);
            Assert.assertEquals(0.0, line.getDistance(), 0.0001);
        }

        @Test
        public void fiveDistance() {
            Line line = new Line(5, 1, 0, 1);
            Assert.assertEquals(5.0, line.getDistance(), 0.0001);
        }

        @Test
        public void areParallel() {
            Line line1 = new Line(0,5,1, 5);
            Line line2 = new Line(0,3,1,3);
            Assert.assertTrue(line1.parallelTo(line2));
        }

        @Test
        public void arentParallel() {
            Line line1 = new Line(0,0,2,2);
            Line line2 = new Line(0,2,2,0);
            Assert.assertFalse(line1.parallelTo(line2));
        }
}


