
public class MoneyCard extends Card
{
	private int money;

	/**
	 * @param money
	 */
	public MoneyCard(String message, int money) {
		super(message);
		this.money = money;
	}

	/**
	 * @return the money
	 */
	public int getMoney() {
		return money;
	}

	/**
	 * @param money the money to set
	 */
	public void setMoney(int money) {
		this.money = money;
	}

	public void action(Player player)
	{
		player.setScore(player.getScore() + money);
	}
	
	public boolean equals(MoneyCard other)
	{
		super.equals(other);
		
		if (other == null)
			return false;
		if (other.getClass() == this.getClass())
		{
			MoneyCard otherCard = (MoneyCard) other;
			return money == otherCard.getMoney();
		}
		else
		{
			return false;
		}
	}
}
