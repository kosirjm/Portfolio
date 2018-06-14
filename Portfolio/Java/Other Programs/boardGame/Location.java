
public class Location
{
	private int x;
	private int y;

	/**
	 * Fully specified constructor
	 * @param x x
	 * @param y y
	 */
	public Location(int x, int y)
	{
		this.x = x;
		this.y = y;
	}


	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}


	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}


	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}


	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}


	/**
	 * Checks to see if the passed in location is equal to this one
	 * @param other location being compared
	 * @return true if equal, false if not
	 */
	public boolean equals(Location other)
	{
		if (other == null)
			return false;
		if (other.getClass() == this.getClass())
		{
			Location otherLocation = (Location) other;
			return x == otherLocation.getX() && y == otherLocation.getY();
		}
		else
		{
			return false;
		}
	}

	/**
	 * Returns a String version of the location
	 * @return the string representation of the location
	 */
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}
