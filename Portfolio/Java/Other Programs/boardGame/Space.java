
public class Space 
{
	private String name;
	private Location location;

	/**
	 * @param name
	 * @param location
	 */
	public Space(String name, Location location) 
	{
		super();
		this.name = name;
		this.location = location;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	public boolean equals(Space other)
	{
		if (other == null)
			return false;
		if (other.getClass() == this.getClass())
		{
			Space otherSpace = (Space) other;
			return location == otherSpace.getLocation()
					&& name.equals(otherSpace.getName());
		}
		else
		{
			return false;
		}
	}
}
