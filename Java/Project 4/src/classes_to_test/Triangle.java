package classes_to_test;

public class Triangle extends Shape {

    public Triangle(Point[] p) {
        super(p);
    }

    public Triangle(Triangle triangle) {
        super(triangle);
    }

    @Override
    public double getArea() {
        double s = 0.5 * getPerimeter();
        double a = super.getVertex(0).getDistance(super.getVertex(1));
        double b = super.getVertex(1).getDistance(super.getVertex(2));
        double c = super.getVertex(2).getDistance(super.getVertex(0));

        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double getPerimeter() {
        return super.getVertex(0).getDistance(super.getVertex(1)) + super.getVertex(1).getDistance(super.getVertex(2))
                + super.getVertex(2).getDistance(super.getVertex(0));
    }

}
