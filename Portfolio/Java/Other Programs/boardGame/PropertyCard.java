
public class PropertyCard extends Card
{
	private Property property;

	/**
	 * @param message
	 * @param property
	 */
	public PropertyCard(String message, Property property) {
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
		if(player.getProperties().contains(this.property))
		{
			player.setScore(player.getScore() + property.getRent());
		}
		else
		{
			player.addProperty(property);
			property.setOwned(true);
		}
	}
	}
	public boolean equals(PropertyCard other)
	{
		super.equals(other);

		if (other == null)
			return false;
		if (other.getClass() == this.getClass())
		{
			PropertyCard otherCard = (PropertyCard) other;
			return property == otherCard.getProperty();
		}
		else
		{
			return false;
		}
	}
}
