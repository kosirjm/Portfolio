package zoo;

/**
 * @author Jonathan Kosir
 * @date Oct 1, 2012
 * @Project Homework 3
 * @class CSE 271
 */
public class Cat extends Animal {

    /**
     * The constructor for the Cat class
     *
     * @param weight the weight of the animal
     * @param feedingCoeff - the feeding coeff of the animal
     */
    public Cat(double weight, double feedingCoeff) {
        super(weight, feedingCoeff);
    }

    @Override
    /**
     * @Override Overrides the abstract eat method of animal eats its weight
     * times its feedingCoeff
     */
    public double eat() {
        return weight * feedingCoeff;
    }

}
