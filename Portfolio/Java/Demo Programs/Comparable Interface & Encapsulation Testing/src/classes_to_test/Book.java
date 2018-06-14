package classes_to_test;

/**
 * The Book class for Project 4 in CSE 271
 *
 * @author Jonathan Kosir
 * @date Oct, 17 2012
 */
public class Book implements Comparable {

    private String author;
    private String title;
    private int numPages;

    /**
     * The book constructor
     *
     * @param author - the author who wrote the book
     * @param title - the title of the book
     * @param numPages - the number of pages in the book
     */
    public Book(String title, String author, int numPages) {
        super();
        this.author = author;
        this.title = title;
        this.numPages = numPages;
    }

    /**
     * The book class' copy constructor
     *
     * @param book - an object of book to be copied
     */
    public Book(Book book) {
        this(book.title, book.author, book.numPages);
    }

    /**
     * Gets the authors name
     *
     * @return the author - of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the books author to input given
     *
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the books title
     *
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the books title to input given
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the books number of pages
     *
     * @return the numPages in the book
     */
    public int getNumPages() {
        return numPages;
    }

    /**
     * Sets the books number of pages to input given
     *
     * @param numPages the numPages to set
     */
    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    /**
     * Compares this book object to another boom object by author then title
     * then page number
     *
     * @param object to be compared must be book or will return error
     */
    @Override
    public int compareTo(Object o) {
        if (o == null) {
            throw new RuntimeException("null reference comparison");
        }
        if (!(o instanceof Book)) {
            throw new ClassCastException("Invalid class type for Book");
        }

        Book b = (Book) o;

        if (author.equals(b.author)) {
            if (title.equals(b.title)) {
                if (numPages < b.numPages) {
                    return -1;
                } else if (numPages > b.numPages) {
                    return 1;
                } else {
                    return 0;
                }

            } else {
                return title.compareTo(b.title);
            }
        } else {
            return author.compareTo(b.author);
        }

    }
}
