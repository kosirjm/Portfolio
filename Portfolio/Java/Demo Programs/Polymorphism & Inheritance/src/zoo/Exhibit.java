package zoo;

/**
 * @author Jonathan Kosir
 * @date Oct 1, 2012
 * @Project Homework 3
 * @class CSE 271
 */
public class Exhibit {

    private Animal[] animals;
    private String type;
    private int amount;

    /**
     * The constructor for the exhibit class
     *
     * @param animals an array list of animals in the exhibit
     * @param type the type of animal in the exhibit
     * @param amount the ammount of those animals in the exhibit
     */
    public Exhibit(Animal[] animals, String type, int amount) {
        super();
        this.animals = animals;
        this.type = type;
        this.amount = amount;
    }

    /**
     * Gets the type of the animal in the exhibit
     *
     * @return the type of the animal
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of animal in that exhibit
     *
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Returns the number of animals in that exhibit
     *
     * @return the amount of animals
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the ammount of animals iin that exhibit
     *
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Returns an animal at the index of the number given in the param
     *
     * @return the animal of the given index
     * @param the index of the animal you would like returned
     */
    public Animal getAnimals(int num) {
        return animals[num];
    }

    /**
     * Gets the array of animals for the exhibit
     *
     * @return the animals
     */
    public Animal[] getAnimalsArray() {
        return animals;
    }

    /**
     * Sets the array of animals for the exhibit
     *
     * @param animals the animals to set
     */
    public void setAnimals(Animal[] animals) {
        this.animals = animals;
    }

}
