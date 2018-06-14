
public class GoodBadSpace extends Space
{
	private boolean isGood;

	/**
	 * @param name
	 * @param location
	 * @param isGood
	 */
	public GoodBadSpace(String name, Location location, boolean isGood) 
	{
		super(name, location);
		this.isGood = isGood;
	}

	/**
	 * @return the isGood
	 */
	public boolean isGood() {
		return isGood;
	}

	/**
	 * @param isGood the isGood to set
	 */
	public void setGood(boolean isGood) {
		this.isGood = isGood;
	}

	public boolean equals(GoodBadSpace other)
	{
		super.equals(other);
		
		if (other == null)
			return false;
		if (other.getClass() == this.getClass())
		{
			GoodBadSpace otherSpace = (GoodBadSpace) other;
			return isGood = otherSpace.isGood();
		}
		else
		{
			return false;
		}
	}
}
