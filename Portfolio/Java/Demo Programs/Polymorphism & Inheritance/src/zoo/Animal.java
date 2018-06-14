package zoo;

/**
 * @author Jonathan Kosir
 * @date Oct 1, 2012
 * @Project Homework 3
 * @class CSE 271
 */
public abstract class Animal {

    protected double weight;
    protected final double feedingCoeff;

    /**
     * Constructor for the abstract animal class
     *
     * @param weight
     * @param feedingCoeff
     */
    public Animal(double weight, double feedingCoeff) {
        super();
        this.feedingCoeff = feedingCoeff;
        this.weight = weight;
    }

    /**
     * An abstract method which simulates a animal eating. Certain animals eat
     * differently and or consume different amounts
     *
     * @return a double the amount the animal ate
     */
    public abstract double eat();

    /**
     * Gets the wieght of the animal
     *
     * @return the weight of the animal
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Sets the wieght of a particular animal
     *
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Gets the feeding Coeff of a animal
     *
     * @return the feedingCoeff
     */
    public double getFeedingCoeff() {
        return feedingCoeff;
    }

}
