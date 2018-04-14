package lab10;

import java.util.Random;
/**
 * Lab 10
 * @author Jonathan Kosir
 * @date Nov 16 2012
 */
public class Lab10 {

	/**
	 * Main File
	 * @param args
	 */
	public static void main(String[] args) {
		Random rnd = new Random(0);
		int[] a = new int[10];
		int[] b = new int[a.length];
		
		for (int i = 0; i < a.length; i++) {
			a[i] = rnd.nextInt(10);
			b[i] = rnd.nextInt(10);
		}
		
		System.out.println("Unsorted array a: " + array2string(a));
		insertionSort(a);
		System.out.println("Insertion sorted array: " + array2string(a));

		System.out.println();
		
		System.out.println("Unsorted array b: " + array2string(b));
		selectionSort(b);
		System.out.println("Selection sorted array: " + array2string(b));
	}
	/**
	 * Turns an int array into a string
	 * @param number the integer array to be 
	 * converted into a toString
	 * @return the array in a string
	 */
	public static String array2string(int[] number)
	{
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < number.length; i++)
		{
			builder.append(" " + number[i]);
		}
		return builder.toString();
	}
	/**
	 * Uses selection sort to sort an array
	 * @param sort the array to be sorted
	 */
	public static void selectionSort(int[] sort)
	{
		int minArray;
		int temp;
		for(int i = 0; i < sort.length - 1; i++)
		{
			minArray = i;
		for(int j = i+1; j < sort.length; j++)
		{
			if(sort[j] < sort[minArray])
			{
				minArray = j;
			}
		}
			 temp = sort[i];
             sort[i] = sort[minArray];
             sort[minArray] = temp;
		}
	}
	
	/**
	 * Uses insertionSort to sort an array
	 * @param sort the int array to be sorted
	 */
	public static void insertionSort(int[] sort)
	{
		int temp = 0;
		int j;
		for(int i = 1; i < sort.length; i++)
		{
			temp = sort[i];
			j = i;
			while(j > 0 && sort[j-1] > temp)
			{
				
				sort[j] = sort[j-1];
				j--;
			}
			sort[j] = temp;
		}
	}
	
}
