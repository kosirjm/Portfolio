
public class Property extends Space
{
private int rent;
private int purchase;
private boolean isOwned;

/**
 * @param name
 * @param location
 * @param rate
 * @param purchase
 * @param isOwned
 */
public Property(String name, Location location, int rent, int purchase,
		boolean isOwned) {
	super(name, location);
	this.rent = rent;
	this.purchase = purchase;
	this.isOwned = isOwned;
}

/**
 * @return the rent
 */
public int getRent() {
	return rent;
}

/**
 * @param rate the rate to set
 */
public void setRate(int rent) {
	this.rent = rent;
}

/**
 * @return the purchase
 */
public int getPurchase() {
	return purchase;
}

/**
 * @param purchase the purchase to set
 */
public void setPurchase(int purchase) {
	this.purchase = purchase;
}

/**
 * @return the isOwned
 */
public boolean isOwned() {
	return isOwned;
}

/**
 * @param isOwned the isOwned to set
 */
public void setOwned(boolean isOwned) {
	this.isOwned = isOwned;
}

public boolean equals(Property other)
{
	super.equals(other);
	
	if (other == null)
		return false;
	if (other.getClass() == this.getClass())
	{
		Property otherSpace = (Property) other;
		return isOwned = otherSpace.isOwned()
				&& rent == otherSpace.getRent()
				&& purchase == otherSpace.getPurchase();
	}
	else
	{
		return false;
	}
}
}
