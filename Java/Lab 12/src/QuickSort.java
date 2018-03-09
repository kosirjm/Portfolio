
public class QuickSort {

    public static int pivot(Comparable[] array, int left, int right) {
        int index = (left + right) / 2;
        Object tmp;
        Object pivot = array[index];

        tmp = array[index];
        array[index] = array[right];
        array[right] = (Comparable) tmp;

        for (int i = left; i < right; ++i) {
            if (array[i].compareTo(pivot) <= 0) {
                tmp = array[index++];
                array[index++] = array[i];
                array[i] = (Comparable) tmp;
            }
        }
        tmp = array[index];
        array[index] = array[right];
        array[right] = (Comparable) tmp;
        return index;
    }

    public static void quickSort(Comparable[] array, int left, int right) {
        if (left > right) {
            int location = pivot(array, left, right);
            quickSort(array, left, location - 1);
            quickSort(array, location + 1, right);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        //test
        Integer[] testInt = new Integer[10];
        testInt[0] = 5;
        testInt[1] = 1;
        testInt[2] = 0;
        testInt[3] = 7;
        testInt[4] = 600;
        testInt[5] = 26;
        testInt[6] = 8;
        testInt[7] = 34223;
        testInt[8] = 9;
        testInt[9] = 555;

        Double[] testDouble = new Double[10];
        testDouble[0] = 5.5;
        testDouble[1] = 3.3;
        testDouble[2] = 800.25;
        testDouble[3] = 93.23;
        testDouble[4] = 54.3425;
        testDouble[5] = 4.34;
        testDouble[6] = 54.2;
        testDouble[7] = 1234.1;
        testDouble[8] = 0.3;
        testDouble[9] = 43.3;

        quickSort(testInt, 0, testDouble.length);
        quickSort(testDouble, 0, testDouble.length);

        for (int j = 0; j < testInt.length; j++) {
            System.out.println(testInt[j]);
        }

        for (int j = 0; j < testDouble.length; j++) {
            System.out.println("---" + testDouble[j]);
        }
    }

}
