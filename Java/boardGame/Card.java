
public class Card 
{
	private String message;

	/**
	 * @param message
	 */
	public Card(String message) 
	{
		super();
		this.message = message;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public void action(Player player)
	{
		
	}
	
	/**
	 * Checks to see if the passed in location is equal to this one
	 * @param other location being compared
	 * @return true if equal, false if not
	 */
	public boolean equals(Card other)
	{
		if (other == null)
			return false;
		if (other.getClass() == this.getClass())
		{
			Card otherCard = (Card) other;
			return message.equals(otherCard.getMessage());
		}
		else
		{
			return false;
		}
	}
}
