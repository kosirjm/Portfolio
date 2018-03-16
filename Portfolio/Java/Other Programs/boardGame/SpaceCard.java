
public class SpaceCard extends Card
{
	private int space;

	/**
	 * @param message
	 * @param space
	 */
	public SpaceCard(String message, int space) {
		super(message);
		this.space = space;
	}

	/**
	 * @return the space
	 */
	public int getSpace() {
		return space;
	}

	/**
	 * @param space the space to set
	 */
	public void setSpace(int space) {
		this.space = space;
	}

	public void action(Player player)
	{
		
	}
	
	public boolean equals(SpaceCard other)
	{
		super.equals(other);
		
		if (other == null)
			return false;
		if (other.getClass() == this.getClass())
		{
			SpaceCard otherCard = (SpaceCard) other;
			return space == otherCard.getSpace();
		}
		else
		{
			return false;
		}
	}
}
