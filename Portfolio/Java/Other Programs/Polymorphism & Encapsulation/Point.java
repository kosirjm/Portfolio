package Shape;
/**
 * The Point class for Project 2 in CSE 271
 * @author Jonathan Kosir
 *@date Sept, 17 2012
 */
public class Point 
{
	double x;
	double y;


	/**
	 * Constructor for the point class 
	 * @param x - the x coordinate
	 * @param y - the y coordinate
	 */
	public Point(double x, double y) 
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Copy Constructor for the point class 
	 * @param p - the point object which is going 
	 * to be copied
	 */
	public Point(Point p)
	{
		this(p.x,p.y);
	}

	/**
	 * @return the x value
	 */
	public double getX() {
		return x;
	}


	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}


	/**
	 * @return the y value
	 */
	public double getY() {
		return y;
	}


	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Calculates the distance between to Point objects using
	 * the mathmateical formula to do so
	 * @param p - the point object which will be evaluated 
	 * against the point object being called on to calculate the distance
	 * between those two points
	 * @return this returns the distance between the two objects
	 */
	public double getDistance(Point p)
	{
		double xDistance = Math.abs(this.x - p.x);
		double yDistance = Math.abs(this.y - p.y);
		return Math.sqrt((xDistance*xDistance)+(yDistance*yDistance));
	}

	/**
	 * Moves a point object to a new location
	 * @param x - the x coordibnate in which you would like to move to
	 * @param y - the y coordinate which you would like to move to
	 */
	public void moveTo(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Checks if two point objects are equal
	 * @param p - the point object to be evaluated against
	 * @return a boolean if the two object are or are not equal
	 */
	public boolean equals(Point p) 
	{
		return ((p.x == x) && (p.y == y));
	}


	/**
	 * A toString method for the Point object
	 * Prints out a nice formatted string
	 */
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	};


}
