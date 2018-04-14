package part_a;

import java.util.Comparator;
import java.lang.Object;

/*
 * Jonathan Kosir
 * Cities
 * HW 5
 * October 29, 2012
 */
public class CityCompareByName implements Comparator {

    /**
     * Compares by city name
     *
     * @param An object to have name of city compared comppared
     * @param An object to have name of city compared comppared
     * @return numbers accoring to the objects orer
     */
    public int compare(Object o, Object o1) {
        if (o == null || o1 == null) {
            throw new RuntimeException("null reference comparison");
        }
        if (!(o instanceof City) || !(o1 instanceof City)) {
            throw new RuntimeException("Invalid class type for City");
        }
        City c1 = (City) o;
        City c2 = (City) o1;

        if (c1.getCityName().equals(c2.getCityName())) {
            return (c1.getStateName().compareTo(c2.getStateName()));
        } else {
            return c1.getCityName().compareTo(c2.getCityName());
        }

    }

}
