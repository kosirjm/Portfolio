package zoo;

/**
 * @author Jonathan Kosir
 * @date Oct 1, 2012
 * @Project Homework 3
 * @class CSE 271
 */
public abstract class Reptile extends Animal {

    /**
     * The constructor for the Reptile class
     *
     * @param weight the weight of the animal
     * @param feedingCoeff - the feeding coeff of the animal
     */
    public Reptile(double weight, double feedingCoeff) {
        super(weight, feedingCoeff);
    }

}
