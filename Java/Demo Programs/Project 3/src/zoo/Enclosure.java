package zoo;

import java.util.ArrayList;

/**
 * @author Jonathan Kosir
 * @date Oct 1, 2012
 * @Project Homework 3
 * @class CSE 271
 */
public class Enclosure {

    private String name;
    private ArrayList<Exhibit> exhibits;
    private ArrayList<Integer> feedingTimes;

    /**
     * The constructor for the Enclosure Class
     *
     * @param exhibits - all the exhibits i9n the enclosure
     * @param name - the name of the enclosure
     * @param feeding times - An arraylist of all the feeding times for that
     * enclosure
     */
    public Enclosure(String name, ArrayList<Exhibit> exhibits, ArrayList<Integer> feedingTimes) {
        super();
        this.name = name;
        this.exhibits = exhibits;
        this.feedingTimes = feedingTimes;
    }

    /**
     * Returns the name of the enclosure
     *
     * @return the name of the enclosure
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the enclosure
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets all the feeding times
     *
     * @return the feedingTimes
     */
    public ArrayList<Integer> getFeedingTimes() {
        return feedingTimes;
    }

    /**
     * Sets akll the feeding times
     *
     * @param feedingTimes the feedingTimes to set
     */
    public void setFeedingTimes(ArrayList<Integer> feedingTimes) {
        this.feedingTimes = feedingTimes;
    }

    /**
     * Gets all the exhibits in the enclosure
     *
     * @return the exhibits
     */
    public ArrayList<Exhibit> getExhibits() {
        return exhibits;
    }

    /**
     * Sets all the exhibits in the enclosure
     *
     * @param exhibits the exhibits to set
     */
    public void setExhibits(ArrayList<Exhibit> exhibits) {
        this.exhibits = exhibits;
    }

}
