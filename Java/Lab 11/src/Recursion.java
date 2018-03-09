
/**
 *
 * @author Jonathan Kosir
 * Lab 11
 * Recursuion kth spot
 * November 30th 2012
 *
 */
public class Recursion {

    /**
     * Recursive algorithm to find kth smallest number in an array
     *
     * @param numbers is the array to look through
     * @param spot is which smallest spot you want (kth)
     * @param arraySpot the current position in the array, to start must be set
     * at zero
     *
     * @return the number in that kth spot
     */
    public static int numberSmallest(int[] numbers, int spot, int arraySpot) {
        int number = 0;
        int numbersSmaller = 0;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[arraySpot] > numbers[i]) {
                numbersSmaller = numbersSmaller + 1;
            }
        }
        if (numbersSmaller == (spot - 1)) {
            number = numbers[arraySpot];
            return number;
        } else {
            arraySpot = arraySpot + 1;
            number = numberSmallest(numbers, spot, arraySpot);
        }

        return number;

    }

    /**
     * Main Method To Test Algorithm
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] numbers = new int[8];
        numbers[0] = -1;
        numbers[1] = -20;
        numbers[2] = 10;
        numbers[3] = 30;
        numbers[4] = 25;
        numbers[5] = -5;
        numbers[6] = 6;
        numbers[7] = 0;

        System.out.println(numberSmallest(numbers, 5, 0));
    }

}
