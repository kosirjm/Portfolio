/**
 * This is the space Class for my simple boggle application
 * It is each "space" in the 4x4 boggle board
 * @author Jonathan Kosir
 * @date December 02, 2012
 * CSE 271
 */
public class Space 
{

	private String letter;
	private boolean used;

	/**
	 * The space class for the Boggle Board
	 * @param letter the letter which is "face up"
	 * on the die
	 * @param used If the letter has been looked at 
	 * during the recursive read.  Used so letters arent
	 * used twice for one word.
	 */
	public Space(String letter, boolean used) {
		super();
		this.letter = letter;
		this.used = used;
	}

	/**
	 * Copy Constructor for the space class
	 * @param spaceC the Space to be copied
	 */
	public Space(Space spaceC)
	{
		letter = spaceC.letter;
		used = spaceC.used;
	}

	/**
	 * The getter method for the letter variable
	 * @return the letter which is up
	 */
	public String getLetter() 
	{
		return letter;
	}

	/**
	 * Sets the letter that is "face up" in the space
	 * @param letter the letter to set
	 */
	public void setLetter(String letter) 
	{
		this.letter = letter;
	}

	/**
	 * Returns if the space has been used in the recursive read
	 * @return the used variable if its been used in the recursive read
	 */
	public boolean isUsed() {
		return used;
	}
	/**
	 * Sets if the space has been used in the recursive read
	 * @param used the used to set
	 */
	public void setUsed(boolean used) {
		this.used = used;
	}

	/**
	 * Quick toString method created by eclipse
	 * modify to liking if needed
	 * @return A nice to String created by eclipse for
	 * the space class
	 */
	@Override
	public String toString() {
		return "Space [letter=" + letter + ", used=" + used + "]";
	}

	/**
	 * Quick equals method created by eclipse
	 * modify to liking if needed
	 * @param the object to be checked if it is equal
	 * @return if the object is equal or not
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Space other = (Space) obj;
		if (letter == null) {
			if (other.letter != null)
				return false;
		} else if (!letter.equals(other.letter))
			return false;
		if (used != other.used)
			return false;
		return true;
	}


}
