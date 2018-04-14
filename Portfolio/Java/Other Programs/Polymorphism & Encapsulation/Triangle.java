package Shape;


/**
 * The Triangle class for Project 2 in CSE 271
 * @author Jonathan Kosir
 *@date Sept, 17 2012
 */
public class Triangle extends Shape
{
	private Point a;
	private Point b;
	private Point c;

	/**
	 * A constructor for the Triangle Class accepting an
	 * Array of Points
	 * @param p - accepts an array of Point objects
	 */
	public Triangle(Point[] p) 
	{
		super(p);
	}


	/**
	 * Gets the area of a Triangle object
	 * @return returns a double which represents the area of the
	 * triangle
	 */
	public double getArea()
	{
		sortX(vertices);
		double length = Math.abs((vertices[0].getX())-(vertices[vertices.length - 1].getX()));
		sortY(vertices);
		double hieght = Math.abs((vertices[1].getY())-(vertices[vertices.length - 1].getY()));
		double area = (length*hieght)/2;
		return area;
	}

}
