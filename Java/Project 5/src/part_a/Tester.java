package part_a;

import java.util.Arrays;
import java.util.Collections;

/*
 * Jonathan Kosir
 * Cities
 * HW 5
 * October 29, 2012
 */
public class Tester {

    public static void main(String[] args) {

        City[] cities = new City[4];

        // first array
        cities[0] = new City("Cincinnati", "Ohio", 333336);
        cities[1] = new City("Chicago", "Illinois", 2896016);
        cities[2] = new City("Boston", "Massachusetts", 589141);
        cities[3] = new City("Rochester", "New York", 207294);

        //sort by population
        Arrays.sort(cities);
        System.out.println("\nCities sorted by population: \n");
        printCities(cities);

        //sort by name
        System.out.println("\nCities sorted by name: \n");
        Arrays.sort(cities, new CityCompareByName());
        printCities(cities);

        City[] newCities = createNewArray(cities, 4);

        // add more to the existing array
        newCities[4] = new City("Rochester", "Minnesota", 103486);
        newCities[5] = new City("San Francisco", "California", 808976);
        newCities[6] = new City("Dallas", "Texas", 6606727);
        newCities[7] = new City("Lawrence", "Kansas", 92048);

        // sort by population
        System.out.println("\nCities sorted by population: \n");

        Arrays.sort(newCities);
        printCities(newCities);

        // sort by name
        System.out.println("\nCities sorted by name: \n");

        Arrays.sort(newCities, new CityCompareByName());
        printCities(newCities);

    }

    /**
     * Prints all the objects in the array
     *
     * @param An array object
     * @return all items of that array
     */
    public static void printCities(City[] c) {
        City[] temp = new City[c.length];
        for (int i = 0; i < c.length; i++) {
            temp[i] = c[i];
            System.out.println(temp[i]);
        }

    }

    /**
     * Creates a new array
     *
     * @param An array object
     * @param number of array spots wamted to be added to the already accouring
     * one
     * @returnnew array with more spots
     */
    public static City[] createNewArray(City[] orig, int n) {
        City[] temp = new City[orig.length + n];
        for (int i = 0; i < orig.length; i++) {
            temp[i] = orig[i];
        }
        return temp;
    }

}
