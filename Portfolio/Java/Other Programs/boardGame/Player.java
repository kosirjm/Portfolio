
public class Player 
{
	private String name;
	private int score;
	private Space space;
	private String image;

	/**
	 * @param name
	 * @param score
	 * @param space
	 * @param image
	 */
	public Player(String name, int score, Space space, String image) 
	{
		super();
		this.name = name;
		this.score = score;
		this.space = space;
		this.image = image;
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
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
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

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	public boolean equals(Player other)
	{
		super.equals(other);
		
		if (other == null)
			return false;
		if (other.getClass() == this.getClass())
		{
			Player otherPlayer = (Player) other;
			return  name.equals(otherPlayer.getName())
					&& score == otherPlayer.getScore()
					&& space == otherPlayer.getSpace()
					&& image.equals(otherPlayer.getImage());
		}
		else
		{
			return false;
		}
	}
}
