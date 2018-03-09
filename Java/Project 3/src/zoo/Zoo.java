package zoo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Jonathan Kosir
 * @date Oct 1, 2012
 * @Project Homework 3
 * @class CSE 271
 */
public class Zoo {

    private ArrayList<Enclosure> enclosures = new ArrayList<Enclosure>();

    /**
     * @param enclosures
     * @throws IOException
     */
    public Zoo(String fileName) throws IOException {
        Scanner input = new Scanner(new File(fileName));

        //Reads the file until no text is left
        while (input.hasNext()) {
            ArrayList<Integer> feedingTimes = new ArrayList<Integer>();
            ArrayList<Exhibit> exhibits = new ArrayList<Exhibit>();
            ArrayList<Double> animalWieghts = new ArrayList<Double>();

            int exhibitAmmount;

            String enclosureName = input.nextLine();
            String temp = input.nextLine();

            //Used try catch because a problem occured with "Show me the monkey exhibit"
            //Even though i told to read entire line scanner would not
            //So it would give a input mismatch error  because the exhibit ammount
            //would pick up some of the monkey text
            //so did try catch if mismatch occured ad it to enclosure name and
            //then assign exhibit ammount
            try {
                exhibitAmmount = Integer.parseInt(temp);
            } catch (NumberFormatException e) {
                enclosureName = enclosureName + temp;
                exhibitAmmount = input.nextInt();
            }

            feedingTimes.clear();
            //Adds values to wieght arrayList
            while (input.hasNextInt()) {
                feedingTimes.add(input.nextInt());
            }

            //Creates all the enclosures and the exhibits in it
            for (int i = 0; i < exhibitAmmount; i++) {
                String exhibitName = input.next();
                int animalAmmount = input.nextInt();

                animalWieghts.clear();
                //Creates the wieght array
                while (input.hasNextDouble()) {
                    animalWieghts.add(input.nextDouble());
                }
                Animal[] animals = new Animal[animalAmmount];

                //Checks all the animals and what the animal type is and then creates that animal
                //Then fills all the exhibits with those animals
                for (int j = 0; j < animalAmmount; j++) {
                    switch (exhibitName.toLowerCase()) {
                        case "gibbon":
                            animals[j] = new Gibbon(animalWieghts.get(j));
                            break;
                        case "jaguar":
                            animals[j] = new Jaguar(animalWieghts.get(j));
                            break;
                        case "ocelot":
                            animals[j] = new Ocelot(animalWieghts.get(j));
                            break;
                        case "snake":
                            animals[j] = new Snake(animalWieghts.get(j));
                            break;
                        case "lizard":
                            animals[j] = new Lizard(animalWieghts.get(j));
                            break;
                    }
                }
                exhibits.add(new Exhibit(animals, exhibitName, animalAmmount));
            }
            enclosures.add(new Enclosure(enclosureName, exhibits, feedingTimes));
        }
        input.close();
    }

    /**
     * Gets all the enclosures in the zoo
     *
     * @return the enclosures
     */
    public ArrayList<Enclosure> getEnclosures() {
        return enclosures;
    }

    /**
     * Sets all the enclosures in the zoo
     *
     * @param enclosures the enclosures to set
     */
    public void setEnclosures(ArrayList<Enclosure> enclosures) {
        this.enclosures = enclosures;
    }

}
