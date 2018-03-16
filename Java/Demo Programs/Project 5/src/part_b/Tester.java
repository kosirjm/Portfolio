package part_b;

import java.util.ArrayList;
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

        ArrayList<City> cities = new ArrayList<City>();

        // first array list
        cities.add(new City("Cincinnati", "Ohio", 333336));
        cities.add(new City("Chicago", "Illinois", 2896016));
        cities.add(new City("Boston", "Massachusetts", 589141));
        cities.add(new City("Rochester", "New York", 207294));

        //sort by size
        Collections.sort(cities);

        System.out.println("\nCities corted by population:\n");
        printCities(cities);

        //sort by name 
        Collections.sort(cities, new CityCompareByName());

        System.out.println("\nCities corted by name:\n");
        printCities(cities);

        // add more to the existing array
        cities.add(new City("Rochester", "Minnesota", 103486));
        cities.add(new City("San Francisco", "California", 808976));
        cities.add(new City("Dallas", "Texas", 6606727));
        cities.add(new City("Lawrence", "Kansas", 92048));

        //sort by size
        Collections.sort(cities);

        System.out.println("\nCities corted by population:\n");
        printCities(cities);

        // sort by name
        Collections.sort(cities, new CityCompareByName());

        System.out.println("\nCities corted by name:\n");
        printCities(cities);

        cities.clone();

    }

    /**
     * Prints all the cities in the arraylist given
     *
     * @param An arraylist object
     */
    public static void printCities(ArrayList<City> c) {
        for (int i = 0; i < c.size(); i++) {
            System.out.println(c.get(i));
        }

    }
}
