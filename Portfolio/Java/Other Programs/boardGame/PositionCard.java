
public class PositionCard extends Card 
{
private Space space;

/**
 * @param message
 * @param space
 */
public PositionCard(String message, Space space) {
	super(message);
	this.space = space;
}

/**
 * @return the space
 */
public Space getSpace() {
	return space;
}

/**
 * @param space the space to set
 */
public void setSpace(Space space) {
	this.space = space;
}

public void action(Player player)
{
player.setSpace(space);	
}

public boolean equals(PositionCard other)
{
	super.equals(other);
	
	if (other == null)
		return false;
	if (other.getClass() == this.getClass())
	{
		PositionCard otherCard = (PositionCard) other;
		return space == otherCard.getSpace();
	}
	else
	{
		return false;
	}
}
}
