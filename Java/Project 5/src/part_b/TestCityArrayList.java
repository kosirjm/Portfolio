package part_b;

import java.util.ArrayList;
import junit.framework.TestCase;
import java.util.Collections;

/*
 * Jonathan Kosir
 * Cities
 * HW 5
 * October 29, 2012
 */
public class TestCityArrayList extends TestCase {

    /*
	 * Test what happens when you clone an array list and change population by
	 * ten percent
     */
    public void testClone() {
        ArrayList<City> cities = new ArrayList<City>();

        cities.add(new City("Cincinnati", "Ohio", 333336));
        cities.add(new City("Chicago", "Illinois", 2896016));
        cities.add(new City("Boston", "Massachusetts", 589141));
        cities.add(new City("Rochester", "New York", 207294));

        ArrayList<City> cities2 = (ArrayList<City>) cities.clone();

        for (City city : cities) {
            city.setPopulation((int) (city.getPopulation() * .1));
        }

        assertEquals(cities, cities2);
    }

    /*
	 * Tests what happens when you use copy constructor 
	 * and increase population by ten percent
     */
    public void testCopy() {
        ArrayList<City> cities = new ArrayList<City>();

        cities.add(new City("Cincinnati", "Ohio", 333336));
        cities.add(new City("Chicago", "Illinois", 2896016));
        cities.add(new City("Boston", "Massachusetts", 589141));
        cities.add(new City("Rochester", "New York", 207294));

        ArrayList<City> cities2 = new ArrayList<City>(4);

        for (int i = 0; i < cities.size(); ++i) {
            cities2.add(new City(cities.get(i)));
        }

        for (City city : cities2) {
            city.setPopulation((int) (city.getPopulation() * .1));
        }

        assertFalse(cities.equals(cities2));
    }
}
