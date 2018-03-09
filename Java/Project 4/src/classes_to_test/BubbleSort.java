package classes_to_test;

/**
 * The BubbleSort class for Project 4 in CSE 271
 *
 * @author Jonathan Kosir
 * @date Oct, 17 2012
 */
public class BubbleSort {

    /**
     * A simple bubble sort method using a book objects compareTo method
     *
     * @param objArray - the array to be sorted
     * @throws ClassCastException
     */
    public static void bubbleSort(Object[] objArray) throws ClassCastException {
        for (int j = objArray.length - 1; j > 0; j--) {
            for (int k = 0; k < j; k++) {

                if (((Book) objArray[k]).compareTo(objArray[k + 1]) > 0) {
                    Book temp = (Book) objArray[k];
                    objArray[k] = objArray[k + 1];
                    objArray[k + 1] = temp;
                }

            }
        }
    }
}
