/**
 *
 */
package calculator;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author kosirjm
 *
 */
public class CalculatorTest {

    Calculator calculator = new Calculator();

    public CalculatorTest() {
        System.out.println("This is the constructor call");
    }

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("AAAA");
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        System.out.println("XXXX");
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void tearDown() throws Exception {
        System.out.println("YYYY");
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDownAfterClass() throws Exception {
        System.out.println("BBBB");
    }

    @Test
    public void testAdd() {
        double eps = 1e-12;
        double xd = 7.8;
        double yd = 2.2;
        double sum = calculator.add(xd, yd);

        assertEquals(xd + yd, sum, eps);

        int xI = 7;
        int yI = 2;
        int sumInt = calculator.add(xI, yI);

        assertEquals(xI + yI, sumInt);

    }

    @Test
    public void testSub() {
        double eps = 1e-12;
        double xd = 7.8;
        double yd = 2.2;
        double sub = calculator.sub(xd, yd);

        assertEquals(xd - yd, sub, eps);

        int xI = 7;
        int yI = 2;
        int subInt = calculator.sub(xI, yI);

        assertEquals(xI - yI, subInt);

    }

}
