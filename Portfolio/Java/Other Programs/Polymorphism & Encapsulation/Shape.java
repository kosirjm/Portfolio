package Shape;

/**
 * The Shape class for Project 2 in CSE 271
 * @author Jonathan Kosir
 *@date Sept, 17 2012
 */
public abstract class Shape 
{
	Point[] vertices;

	/**
	 * A Point constructor which takes a copy 
	 * of the point objects in the given array and assigns those
	 * values to the vertices array for the shape class
	 * @param p - the point object array which will give our
	 * shape points
	 */
	public Shape(Point[] p)
	{
		Point[] newVertices = new Point[p.length];
		for(int i = 0; i < p.length; i++)
		{
			newVertices[i] = new Point(p[i]);
		}
		this.vertices = newVertices;
	}

	/**
	 * @return the vertices
	 */
	public Point[] getVertices() {
		return vertices;
	}

	/**
	 * @param vertices the vertices to set
	 */
	public void setVertices(Point[] vertices) {
		this.vertices = vertices;
	}

	/**
	 * Sorts x from smallest to biggest for a given
	 * array of Points
	 * @param p - the array of Points that needs to be sorted
	 * by x
	 */
	void sortX(Point[] p)
	{
		for(int i = 0; i < p.length; i++)
		{
			for(int j = i+1; j < p.length; j++)
			{
				if(p[j].getX() < p[i].getX())
				{
					Point temp = p[i];
					p[i] = p[j];
					p[j] = temp;
				}
			}
		}
	}

	/**
	 * Sorts y from smallest to biggest for a given
	 * array of Points
	 * @param p - the array of Points that needs to be sorted
	 * by y
	 */
	void sortY(Point[] p)
	{
		for(int i = 0; i < p.length; i++)
		{
			for(int j = i+1; j < p.length; j++)
			{
				if(p[j].getY() < p[i].getY())
				{
					Point temp = p[i];
					p[i] = p[j];
					p[j] = temp;
				}
			}
		}
	}

	/**
	 * Abstract method for getting shape areas
	 * @return area
	 */
	public abstract double getArea();
	}

