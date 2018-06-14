package zoo;

/**
 * @author Jonathan Kosir
 * @date Oct 1, 2012
 * @Project Homework 3
 * @class CSE 271
 */
public class Lizard extends Reptile {

    /**
     * The constructor for the Lizard class
     *
     * @param weight the weight of the animal
     */
    public Lizard(double weight) {
        super(weight, .004);
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
