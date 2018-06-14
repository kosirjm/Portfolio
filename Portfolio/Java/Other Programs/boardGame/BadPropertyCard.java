
public class BadPropertyCard extends Card
{
	private Property property;

	/**
	 * @param message
	 * @param property
	 */
	public BadPropertyCard(String message, Property property) {
		super(message);
		this.property = property;
	}

	/**
	 * @return the property
	 */
	public Property getProperty() {
		return property;
	}

	/**
	 * @param property the property to set
	 */
	public void setProperty(Property property) 
	{
		this.property = property;
	}

	public void action(Player player)
	{
		for(int i = 0; i < player.getProperties().size(); i ++)
		{
			if(!player.getProperties().contains(this.property))
			{
				player.setScore(player.getScore() - property.getRent());
			}
			else
			{
				player.removeProperty(property);
				property.setOwned(false);
			}
		}
	}
	public boolean equals(BadPropertyCard other)
	{
		super.equals(other);

		if (other == null)
			return false;
		if (other.getClass() == this.getClass())
		{
			BadPropertyCard otherCard = (BadPropertyCard) other;
			return property == otherCard.getProperty();
		}
		else
		{
			return false;
		}
	}
}

