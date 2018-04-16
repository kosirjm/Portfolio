package part_b;

/*
 * Jonathan Kosir
 * Cities
 * HW 5
 * October 29, 2012
 */
public class City implements Comparable {

    private String cityName;
    private String stateName;
    private int population;

    /**
     * A Constructor for the city class
     *
     * @param pop the population
     * @param name of city
     * @param name of state city is in
     */
    public City(String city, String state, int pop) {
        cityName = city;
        stateName = state;
        population = pop;
    }

    /**
     * A copy constructor for the city class
     *
     * @param the city to be copied
     */
    public City(City other) {
        if (other != null) {
            this.cityName = other.cityName;
            this.stateName = other.stateName;
            this.population = other.population;
        }
    }

    /**
     * Get name of the city
     *
     * @return the city name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Get state name
     *
     * @return the states name
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * Get population of the city
     *
     * @return the population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * Set the population of the city
     *
     * @param population the population to set
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * Sets the cities name
     *
     * @param cityName the cityName to set
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * Sets the states name
     *
     * @param stateName the stateName to set
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * Gives a nice to string for the city
     *
     * @param a nice formated String
     */
    public String toString() {
        return cityName + ", " + stateName + ",\t" + population;
    }

    /**
     * A compare to method which compares population of the city
     *
     * @param An object to have population comppared
     * @return the array in numerical order according to populations
     */
    public int compareTo(Object o) {
        if (o == null) {
            throw new RuntimeException("null reference comparison");
        }
        if (!(o instanceof City)) {
            throw new RuntimeException("Invalid class type for City");
        }

        City c = (City) o;
        if (getPopulation() < c.getPopulation()) {
            return -1;
        } else if (getPopulation() > c.getPopulation()) {
            return 1;
        } else {
            return 0;
        }

    }

}
