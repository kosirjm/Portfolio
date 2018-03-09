
import java.util.Arrays;
import java.util.Random;

/**
 * This class represents one of the 26 dice in the boggle game
 * the die will then fill a space in the boggle board
 * @author Jonathan Kosir
 * @date December 02, 2012
 * CSE 271
 */
public class Die 
{
	private String[] side;
	private boolean isUsed;
	private boolean usedInWord;

	/**
	 * The die has 6 sides and can only be used once
	 * for one round of the game, can only fill one space.
	 * @param side a individual side of the die which will have a letter on it
	 * @param isUsed says if the die had been used in that round of boggle yet
	 */
	public Die(String[] side, boolean isUsed) 
	{
		super();
		this.isUsed = isUsed;
		this.side = new String[side.length];
		for (int i = 0; i < side.length; i++) 
		{
			this.side[i] = new String(side[i]);
		}
	}

	/**
	 * Copy constructor for the die class
	 * @param die the die to be copied
	 */
	public Die(Die die)
	{
		side = die.side;
		isUsed = die.isUsed;
	}

	/**
	 * Gets if the die had been used that round or not
	 * @return the isUsed if it was used that round
	 */
	public boolean isUsed() 
	{
		return isUsed;
	}

	/**
	 * Sets if the die has been used that round
	 * @param isUsed the isUsed to set
	 */
	public void setUsed(boolean isUsed) 
	{
		this.isUsed = isUsed;
	}


	/**
	 * The entire side array that makes up the die
	 * @return the side array of the die
	 */
	public String[] getSides() {
		return side;
	}

	/**
	 * Returns a individual side of the side array
	 * @return the side in the side array
	 * @param the index of the side you want
	 */
	public String getSide(int i) {
		return side[i];
	}

	/**
	 * Gives a String of all the sides on that specific die
	 * @return the side array of the die
	 */
	public String getSideString() 
	{
		String sides = "";
		for (int i = 0; i < side.length; i++) 
		{
			sides += " " + side[i];
		}
		return sides;
	}

	/**
	 * Sets the entire side array to a specific value
	 * @param side the side to set
	 */
	public void setSide(String[] side) {
		this.side = side;
	}

	/**
	 * This method rolls the die, this randomly chooses one
	 * of the 6 values of the die sides array and chooses that side to
	 * be facing "up"
	 * @return the random side that is "up"
	 */
	public String rollDie()
	{
		Random rand = new Random();
		int randSide = rand.nextInt(6);

		return side[randSide];
	}

	/**
	 * Quick toString method created by eclipse
	 * modify to liking if needed
	 * @return a nice toString as created by eclipse
	 */
	@Override
	public String toString() {
		return "Die [side=" + Arrays.toString(side) + ", isUsed=" + isUsed
				+ ", usedInWord=" + usedInWord + ","
				+ ", isUsed()=" + isUsed() + ", getSide()="
				+ ", rollDie()=" + rollDie() + "]";
	}

	/**
	 * Quick equals method created by eclipse
	 * modify to liking if needed
	 * @return a boolean if the object is equal
	 * @param to object to be check if it is equal to
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Die other = (Die) obj;
		if (isUsed != other.isUsed)
			return false;
		if (!Arrays.equals(side, other.side))
			return false;
		if (usedInWord != other.usedInWord)
			return false;
		return true;
	}

}
