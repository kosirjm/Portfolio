package Shape;
/**
 * The Point class for Lab 4 in CSE 271
 * @author Jonathan Kosir
 *@date Sept, 21 2012
 */
public class Circle extends Shape
{
double radius;
 
	/**
	 * Constrctor for a circle object
	 * @param p a point array
	 * @param radius - the radius of the circle
	 */
	public Circle(Point[] p, double radius) {
		super(p);
		this.radius = radius;
	}
/**
 * Gets the area of a circle
	@Override
	*/
	public double getArea() {
		double area = 3.14*radius*radius;
		return area;
	}

	/**
	 * @return the radius
	 */
	public double getRadius() {
		return radius;
	}

	/**
	 * @param radius the radius to set
	 */
	public void setRadius(double radius) {
		this.radius = radius;
	}

	

}
