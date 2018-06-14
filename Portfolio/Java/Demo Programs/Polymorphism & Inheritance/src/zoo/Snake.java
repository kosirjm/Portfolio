package zoo;

import java.util.Random;

/**
 * @author Jonathan Kosir
 * @date Oct 1, 2012
 * @Project Homework 3
 * @class CSE 271
 */
public class Snake extends Reptile {

    /**
     * The constructor for the Snake class
     *
     * @param weight the weight of the animal
     */
    public Snake(double weight) {
        super(weight, .8);
    }

    @Override
    /**
     * @Override Overrides the abstract eat method of animal eats differently
     * then most of the other animals it eats by creating a randonm number if
     * the random number is under its feeding coeff then it eats .08 times its
     * wieght other wise it does not eat
     */
    public double eat() {
        Random randomGen = new Random();
        double randomNum = randomGen.nextDouble();
        if (randomNum < feedingCoeff) {
            return (weight * .08);
        } else {
            return 0;
        }
    }
}
