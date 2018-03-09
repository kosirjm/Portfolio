
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import classes_to_test.Book;
import classes_to_test.BubbleSort;

/**
 *
 */
/**
 * The BookTest J-Unit test for Project 4 in CSE 271
 *
 * @author Jonathan Kosir
 * @date Oct, 17 2012
 */
public class BookTest {

    Book[] books;

    /**
     * Sets up a book array
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        //Setup book array to be sorted in test
        books = new Book[6];
        books[0] = new Book("The Hobbit", "J.R.R. Tolkien", 311);
        books[1] = new Book("The Hobbit", "J.R.R. Tolkien", 325);
        books[2] = new Book("The Lord of the Ring: Fellowship of the Ring", "J.R.R. Tolkien", 422);
        books[3] = new Book("The Dark Knight Returns", "Frank Miller", 94);
        books[4] = new Book("Snow Crash", "Neal Stephenson", 468);
        books[5] = new Book("1776", "David McCullough", 375);
    }

    /**
     * Tests my compareTo and bubbleSort methods to make sure the sort correctly
     */
    @Test
    public void test() {

        //Creates a second array to be sorted by Arrays.sort
        Book[] booksNew = new Book[6];
        for (int i = 0; i < books.length; i++) {
            booksNew[i] = books[i];
        }

        //Sorts original array using my bubblesort method
        //Sorts second array by Arrays.sort
        BubbleSort.bubbleSort(books);
        Arrays.sort(booksNew);

        //Checks if arrays are equal
        assertEquals(books, booksNew);
    }

    /**
     * Tests that my bubbleSort method throws correct exception at correct time
     */
    @Test(expected = ClassCastException.class)
    public void testException() {

        //Creates an array of String objects
        Object[] booksObject = new Object[6];
        for (int i = 0; i < booksObject.length; i++) {
            booksObject[i] = new String();
        }

        //Runs bubblesort with newly created String array
        //(String is not a book object therefore should throw error)
        BubbleSort.bubbleSort(booksObject);
    }
}
