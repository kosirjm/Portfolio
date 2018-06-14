package Shape;
/**
 * The Quadrilateral class for Project 2 in CSE 271
 * @author Jonathan Kosir
 *@date Sept, 17 2012
 */
public class Rectangle extends Shape
{
	/**
	 * The constructor for the quadrilateral class
	 * @param p - an array of Point objects which 
	 * creates a quadrilateral extends from shape
	 */
	public Rectangle(Point[] p) 
	{
		super(p);
	}

	/**
	 * Gets the area of a quadrilateral
	 * @return a double which represents the area of the
	 * quadrilateral called upon
	 */
	public double getArea()
	{
		sortX(vertices);
		double length = Math.abs((vertices[0].getX())-(vertices[vertices.length-1].getX()));
		sortY(vertices);
		double hieght = Math.abs((vertices[1].getY())-(vertices[vertices.length-1].getY()));
		double area = (length*hieght);
		return area;
	}

}
