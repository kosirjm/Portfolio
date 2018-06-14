
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import classes_to_test.Point;
import classes_to_test.Triangle;

/**
 * The ShapeTest J-Unit test for Project 4 in CSE 271
 *
 * @author Jonathan Kosir
 * @date Oct, 17 2012
 */
public class ShapeTest {

    Triangle triangle;
    Triangle copyTriangle;
    Point[] points;

    /**
     * Sets up a point array and two triangles
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

        points = new Point[3];
        points[0] = new Point(-1, 0.5);
        points[1] = new Point(2, 2);
        points[2] = new Point(0, 4);
        triangle = new Triangle(points);
        copyTriangle = triangle;
    }

    @Test
    public void testEncapsulation() {

        //Changes points that were used to initialize original triangle
        points[0] = new Point(400, 400);
        points[1] = new Point(300, 60);
        points[2] = new Point(200, 10);
        points[0].setX(400);
        points[1].setX(200);
        points[0].setY(400);
        points = null;

        //Checks against other triangle to make sure values had not changed
        assertEquals(copyTriangle, triangle);
    }
}
