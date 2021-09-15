public class Rectangle implements Shape {
    private Double height;
    private Double length;

    public Rectangle(Double h, Double l) {
        height = h;
        length = l;
    }

    public Double calculateArea() {
        return height * length;
    }

    public void display() {
        int line_len;
        if (length > 15) {
            line_len = 15;
        } else {
            line_len = (int) Math.round(length);
        }
        String line = new String(new char[line_len]).replace('\0', '*');

        System.out.println();
        int high_side;
        if (height < 20) {
            high_side = 10;
        } else {
            high_side = (int)Math.round(height)/2;
        }
        for (int i = 0; i < high_side; i++) {
            System.out.println(line);
        }
        System.out.println();
    }
}
