package manager;
import java.io.IOException;
import java.util.Scanner;
import zoo.Zoo;

/**
 * @author Jonathan Kosir
 * @date Oct 1, 2012
 * @Project Homework 3
 * @class CSE 271
 */
public class Manager {

    private final static int HOURS_IN_A_DAY = 12;
    private final static String READ_IN_FILE = "zoo.txt";

    /**
     * This helper method is a method which checks each enclosure, exhibit and
     * animal eventually at the core gets all the animals for all the exhibits
     * in each enclosure and checks eating time and if its time to eat calls the
     * eating method in the animal abstract class
     *
     * @param zoo - the zoo object which is going to be looped
     * @param time - the time of day it is
     */
    public void longLoop(Zoo zoo, int time) {
        for (int j = 0; j < zoo.getEnclosures().size(); j++) {
            System.out.println("Check " + zoo.getEnclosures().get(j).getName());

            for (int k = 0; k < zoo.getEnclosures().get(j).getFeedingTimes().size(); k++) {
                if (time == zoo.getEnclosures().get(j).getFeedingTimes().get(k)) {
                    for (int l = 0; l < zoo.getEnclosures().get(j).getExhibits().size(); l++) {
                        for (int m = 0; m < zoo.getEnclosures().get(j).getExhibits().get(l).getAnimalsArray().length; m++) {
                            System.out.printf("Feeding time!: "
                                    + "%s"
                                    + " eats " + "%.4f"
                                    + " kilograms of food \n", zoo.getEnclosures().get(j).getExhibits().get(l).getType(),
                                    zoo.getEnclosures().get(j).getExhibits().get(l).getAnimals(m).eat());
                        }
                    }
                }
            }
        }
    }

    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Manager manager = new Manager();
        Scanner input = new Scanner(System.in);
        Zoo zoo = new Zoo(READ_IN_FILE);

        //Next few lines are beginning menu
        System.out.println("There are " + zoo.getEnclosures().size()
                + " enclosures");

        for (int i = 0; i < zoo.getEnclosures().size(); i++) {
            System.out.println(zoo.getEnclosures().get(i).getName());
        }

        System.out.println("Press 'c' to advance simulation, any other character to quit.");
        //------------------------

        //Loops sym
        int i = 0;
        while (true) {
            //takes in input if anyother letter then c quits
            String character = input.next().toLowerCase();
            if (!character.equals("c")) {
                System.out.println("Goodbye!");
                System.exit(0);
            } //----------------------------------------------
            //else does sym
            else {
                //If i is above or equal to 12 must set number to a 12 hour clock system
                //Also calls helper variable to check feeding times and if its time to eat
                if (i >= 12) {
                    int num = i - HOURS_IN_A_DAY;
                    if (num == 0) {
                        num = HOURS_IN_A_DAY;
                    }
                    System.out.println("\nSimulating: " + num + " PM");
                    manager.longLoop(zoo, i);
                } //-------------------------------------
                //Morning times can just keep time as i
                //Also calls helper variable to check feeding times and if its time to eat
                else {
                    int num = i;
                    if (num == 0) {
                        num = HOURS_IN_A_DAY;
                    }
                    System.out.println("\nSimulating: " + num
                            + " AM");
                    manager.longLoop(zoo, i);
                }
                //-------------------------------------------------------------------------

                //adds i and if over 23 hours(11oclock) closes sim
                i++;
                if (i > 23) {
                    System.out.println("\nSimulation for the day is complete, Goodbye!");
                    System.exit(0);
                }
                //-------------------------
            }
        }
    }
}
