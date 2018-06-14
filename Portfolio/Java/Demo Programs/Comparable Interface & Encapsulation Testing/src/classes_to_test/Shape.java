package classes_to_test;

public abstract class Shape {

    private Point[] vertices;

    public Shape(Point[] p) {
        vertices = new Point[p.length];
        for (int i = 0; i < p.length; i++) {
            vertices[i] = new Point(p[i]);
        }
    }

    public Shape(Shape shapeCopy) {
        this(shapeCopy.vertices);
    }

    public Point getVertex(int i) {
        return vertices[i];
    }

    public void setVertex(int i, Point p) {
        vertices[i] = p;
    }

    public abstract double getArea();

    public abstract double getPerimeter();
}
